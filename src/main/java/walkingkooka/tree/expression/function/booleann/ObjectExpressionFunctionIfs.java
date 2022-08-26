/*
 * Copyright 2019 Miroslav Pokorny (github.com/mP1)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package walkingkooka.tree.expression.function.booleann;

import walkingkooka.Cast;
import walkingkooka.tree.expression.ExpressionEvaluationContext;
import walkingkooka.tree.expression.function.ExpressionFunctionParameter;
import walkingkooka.tree.expression.function.ExpressionFunctionParameterKind;
import walkingkooka.tree.expression.function.ExpressionFunctionParameterName;

import java.util.List;

/**
 * A series of multiple conditions, made up of pairs of booleans and the result value. If no positive is found, null
 * is returned.
 * <br>
 * https://exceljet.net/excel-functions/excel-ifs-function
 */
final class ObjectExpressionFunctionIfs<C extends ExpressionEvaluationContext> extends ObjectExpressionFunction<C> {

    /**
     * Instance getter.
     */
    static <C extends ExpressionEvaluationContext> ObjectExpressionFunctionIfs<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private static final ObjectExpressionFunctionIfs INSTANCE = new ObjectExpressionFunctionIfs();

    /**
     * Private ctor
     */
    private ObjectExpressionFunctionIfs() {
        super("ifs");
    }

    @Override
    public Object apply(final List<Object> parameters,
                        final C context) {
        this.checkParameterCount(parameters);

        final List<Object> testValuePairs = TEST_VALUE_PAIRS.getVariable(parameters, 0);
        final int count = testValuePairs.size();

        Object result = null;

        int i = 0;
        for (; ; ) {
            if (count == i) {
                break;
            }
            final Boolean test = (Boolean) parameters.get(i);
            i++;
            if (count == i) {
                break;
            }
            final Object value = parameters.get(i);
            if (test) {
                result = value;
                break;
            }
            i++;
        }

        return result;
    }

    @Override
    public List<ExpressionFunctionParameter<?>> parameters(final int count) {
        return PARAMETERS;
    }

    private final static ExpressionFunctionParameter<Object> TEST_VALUE_PAIRS = ExpressionFunctionParameterName.with("test-value-pairs")
            .variable(Object.class)
            .setKinds(ExpressionFunctionParameterKind.EVALUATE_RESOLVE_REFERENCES);

    private final static List<ExpressionFunctionParameter<?>> PARAMETERS = ExpressionFunctionParameter.list(
            TEST_VALUE_PAIRS
    );
}

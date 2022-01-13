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
import walkingkooka.tree.expression.ExpressionNumber;
import walkingkooka.tree.expression.ExpressionPurityContext;
import walkingkooka.tree.expression.FunctionExpressionName;
import walkingkooka.tree.expression.function.ExpressionFunction;
import walkingkooka.tree.expression.function.ExpressionFunctionContext;
import walkingkooka.tree.expression.function.ExpressionFunctionParameter;
import walkingkooka.tree.expression.function.ExpressionFunctionParameterName;

import java.util.List;

/**
 * Uses the first number as an index to select one of the values in following parameters.
 * <br>
 * https://exceljet.net/excel-functions/excel-choose-function
 */
final class ChooseExpressionFunction<C extends ExpressionFunctionContext> implements ExpressionFunction<Object, C> {

    /**
     * Instance getter.
     */
    static <C extends ExpressionFunctionContext> ChooseExpressionFunction<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private static final ChooseExpressionFunction INSTANCE = new ChooseExpressionFunction();

    /**
     * Private ctor
     */
    private ChooseExpressionFunction() {
        super();
    }

    @Override
    public Object apply(final List<Object> parameters,
                        final C context) {
        this.checkParameterCount(parameters);

        final int index = INDEX.getOrFail(parameters, 0)
                .intValue();

        return VALUES.getVariable(parameters, 1).get(index - 1);
    }

    @Override
    public FunctionExpressionName name() {
        return NAME;
    }

    private final static FunctionExpressionName NAME = FunctionExpressionName.with("choose");

    @Override
    public List<ExpressionFunctionParameter<?>> parameters() {
        return PARAMETERS;
    }

    private final static ExpressionFunctionParameter<ExpressionNumber> INDEX = ExpressionFunctionParameterName.with("index")
            .required(ExpressionNumber.class);

    private final static ExpressionFunctionParameter<Object> VALUES = ExpressionFunctionParameterName.with("values")
            .variable(Object.class);

    private final static List<ExpressionFunctionParameter<?>> PARAMETERS = ExpressionFunctionParameter.list(
            INDEX,
            VALUES
    );

    @Override
    public Class<Object> returnType() {
        return Object.class;
    }

    @Override
    public boolean requiresEvaluatedParameters() {
        return true;
    }

    @Override
    public boolean resolveReferences() {
        return true;
    }

    @Override
    public boolean isPure(final ExpressionPurityContext context) {
        return true;
    }

    @Override
    public String toString() {
        return this.name().toString();
    }
}

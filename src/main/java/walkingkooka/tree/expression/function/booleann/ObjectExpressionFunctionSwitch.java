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
import java.util.Objects;

/**
 * A Switch function which uses the first parameter to scan the key value pairs that follow. if none of these match a default trailing or last value may be used.
 * <br>
 * https://exceljet.net/excel-functions/excel-switch-function
 */
final class ObjectExpressionFunctionSwitch<C extends ExpressionEvaluationContext> extends ObjectExpressionFunction<C> {

    /**
     * Instance getter.
     */
    static <C extends ExpressionEvaluationContext> ObjectExpressionFunctionSwitch<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private static final ObjectExpressionFunctionSwitch INSTANCE = new ObjectExpressionFunctionSwitch();

    /**
     * Private ctor
     */
    private ObjectExpressionFunctionSwitch() {
        super("switch");
    }

    @Override
    public Object apply(final List<Object> parameters,
                        final C context) {
        this.checkParameterCount(parameters);

        final Object test = TEST.getOrFail(parameters, 0, context);
        final CharSequence testCharSequence = test instanceof CharSequence ?
                (CharSequence) test :
                null;

        final List<Object> keyValues = KEY_VALUES.getVariable(parameters, 1);

        final int count = keyValues.size();

        int i = 0;
        Object result = null;

        do {
            if (i == count) {
                throw new IllegalArgumentException("Missing default");
            }

            if (i + 1 == count) {
                result = keyValues.get(i);
                break;
            }

            final Object key = keyValues.get(i);
            i++;
            final Object value = keyValues.get(i);
            i++;

            if (
                    (null != testCharSequence && key instanceof CharSequence) ?
                            context.stringEqualsCaseSensitivity().equals(testCharSequence, (CharSequence) key) :
                            Objects.equals(test, key)
            ) {
                result = value;
                break;
            }

        } while (true);

        return result;
    }

    @Override
    public List<ExpressionFunctionParameter<?>> parameters(final int count) {
        return PARAMETERS;
    }

    private final static ExpressionFunctionParameter<Object> TEST = ExpressionFunctionParameterName.with("test")
            .required(Object.class)
            .setKinds(ExpressionFunctionParameterKind.EVALUATE_RESOLVE_REFERENCES);

    private final static ExpressionFunctionParameter<Object> KEY_VALUES = ExpressionFunctionParameterName.with("key-values")
            .variable(Object.class)
            .setKinds(ExpressionFunctionParameterKind.EVALUATE_RESOLVE_REFERENCES);

    private final static List<ExpressionFunctionParameter<?>> PARAMETERS = ExpressionFunctionParameter.list(
            TEST,
            KEY_VALUES
    );
}

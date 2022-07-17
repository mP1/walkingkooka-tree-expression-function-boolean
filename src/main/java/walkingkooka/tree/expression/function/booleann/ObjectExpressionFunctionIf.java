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
import walkingkooka.tree.expression.function.ExpressionFunctionParameterName;

import java.util.List;

/**
 * A simple if that uses the first parameter when true to select the 2nd parameter or when false selects the third parameter.
 */
final class ObjectExpressionFunctionIf<C extends ExpressionEvaluationContext> extends ObjectExpressionFunction<C> {

    /**
     * Instance getter.
     */
    static <C extends ExpressionEvaluationContext> ObjectExpressionFunctionIf<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private static final ObjectExpressionFunctionIf INSTANCE = new ObjectExpressionFunctionIf();

    /**
     * Private ctor
     */
    private ObjectExpressionFunctionIf() {
        super("if");
    }

    @Override
    public Object apply(final List<Object> parameters,
                        final C context) {
        this.checkParameterCount(parameters);

        return BOOLEAN.getOrFail(parameters, 0) ?
                TRUE.getOrFail(parameters, 1) :
                FALSE.getOrFail(parameters, 2);
    }

    @Override
    public List<ExpressionFunctionParameter<?>> parameters(final int count) {
        return PARAMETERS;
    }

    private final static ExpressionFunctionParameter<Boolean> BOOLEAN = ExpressionFunctionParameter.BOOLEAN;

    private final static ExpressionFunctionParameter<Object> TRUE = ExpressionFunctionParameterName.with("true-value")
            .required(Object.class);

    private final static ExpressionFunctionParameter<Object> FALSE = ExpressionFunctionParameterName.with("false-value")
            .required(Object.class);

    private final static List<ExpressionFunctionParameter<?>> PARAMETERS = ExpressionFunctionParameter.list(
            BOOLEAN,
            TRUE,
            FALSE
    );
}

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
import walkingkooka.collect.list.Lists;
import walkingkooka.tree.expression.ExpressionEvaluationContext;
import walkingkooka.tree.expression.function.ExpressionFunctionParameter;
import walkingkooka.tree.expression.function.ExpressionFunctionParameterName;

import java.util.List;

/**
 * A function that converts the given value into a {@link Boolean}.
 */
final class BooleanExpressionFunctionTo<C extends ExpressionEvaluationContext> extends BooleanExpressionFunction<C> {

    /**
     * Instance getter.
     */
    static <C extends ExpressionEvaluationContext> BooleanExpressionFunctionTo<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private static final BooleanExpressionFunctionTo INSTANCE = new BooleanExpressionFunctionTo();

    /**
     * Private ctor
     */
    private BooleanExpressionFunctionTo() {
        super("boolean");
    }

    @Override
    public Boolean apply(final List<Object> parameters,
                         final C context) {
        this.checkParameterCount(parameters);

        return context.convertOrFail(
                PARAMETER.getOrFail(parameters, 0),
                Boolean.class
        );
    }

    @Override
    public List<ExpressionFunctionParameter<?>> parameters() {
        return PARAMETERS;
    }

    private final static ExpressionFunctionParameter<Object> PARAMETER = ExpressionFunctionParameterName.with("parameter")
            .required(Object.class);

    private final static List<ExpressionFunctionParameter<?>> PARAMETERS = Lists.of(PARAMETER);
}

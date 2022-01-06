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
import walkingkooka.tree.expression.function.ExpressionFunctionContext;
import walkingkooka.tree.expression.function.ExpressionFunctionParameter;

import java.util.List;

/**
 * A function that inverts the a boolean value.
 */
final class BooleanExpressionFunctionNot<C extends ExpressionFunctionContext> extends BooleanExpressionFunction<C> {

    /**
     * Instance getter.
     */
    static <C extends ExpressionFunctionContext> BooleanExpressionFunctionNot<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private static final BooleanExpressionFunctionNot INSTANCE = new BooleanExpressionFunctionNot();

    /**
     * Private ctor
     */
    private BooleanExpressionFunctionNot() {
        super("not");
    }

    @Override
    public Boolean apply(final List<Object> parameters,
                         final C context) {
        this.checkOnlyRequiredParameters(parameters);

        return !PARAMETER.getOrFail(parameters, 0);
    }

    @Override
    public List<ExpressionFunctionParameter<?>> parameters() {
        return PARAMETERS;
    }

    private final static ExpressionFunctionParameter<Boolean> PARAMETER = ExpressionFunctionParameter.BOOLEAN;

    private final static List<ExpressionFunctionParameter<?>> PARAMETERS = ExpressionFunctionParameter.list(
            PARAMETER
    );
}

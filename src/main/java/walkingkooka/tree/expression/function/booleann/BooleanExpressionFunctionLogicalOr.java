/*
 * Copyright 2020 Miroslav Pokorny (github.com/mP1)
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

import java.util.List;

final class BooleanExpressionFunctionLogicalOr<C extends ExpressionEvaluationContext> extends BooleanExpressionFunctionLogical<C> {

    /**
     * Instance getter.
     */
    static <C extends ExpressionEvaluationContext> BooleanExpressionFunctionLogicalOr<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private static final BooleanExpressionFunctionLogicalOr INSTANCE = new BooleanExpressionFunctionLogicalOr();

    /**
     * Private ctor
     */
    private BooleanExpressionFunctionLogicalOr() {
        super("or");
    }

    @Override
    public Boolean apply(final List<Object> parameters,
                         final C context) {
        final int count = parameters.size();
        checkParameterCount(count);

        boolean result = false;

        for (final Boolean value : PARAMETER.getVariable(parameters, 0)) {
            result = result || value;
            if (result) {
                break;
            }
        }

        return result;
    }
}

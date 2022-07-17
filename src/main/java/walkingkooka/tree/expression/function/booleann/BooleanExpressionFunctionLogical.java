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

import walkingkooka.tree.expression.ExpressionEvaluationContext;
import walkingkooka.tree.expression.function.ExpressionFunctionParameter;
import walkingkooka.tree.expression.function.ExpressionFunctionParameterName;

import java.util.List;

/**
 * Base class for any of the supported logical functions such as AND, OR, XOR.
 */
abstract class BooleanExpressionFunctionLogical<C extends ExpressionEvaluationContext> extends BooleanExpressionFunction<C> {

    BooleanExpressionFunctionLogical(final String name) {
        super(name);
    }

    @Override
    public final List<ExpressionFunctionParameter<?>> parameters(final int count) {
        return PARAMETERS;
    }

    final static ExpressionFunctionParameter<Boolean> PARAMETER = ExpressionFunctionParameterName.with("parameter")
            .variable(Boolean.class);

    private final static List<ExpressionFunctionParameter<?>> PARAMETERS = ExpressionFunctionParameter.list(
            PARAMETER
    );

    static void checkParameterCount(final int count) {
        if (0 == count) {
            throw new IllegalArgumentException("Expected at least 1 parameter but got 0");
        }
    }
}

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

import walkingkooka.Either;
import walkingkooka.tree.expression.ExpressionEvaluationContext;
import walkingkooka.tree.expression.FakeExpressionEvaluationContext;

public abstract class BooleanExpressionFunctionTestCase<F extends BooleanExpressionFunction<ExpressionEvaluationContext>> extends ExpressionFunctionTestCase<F, Boolean> {

    BooleanExpressionFunctionTestCase() {
        super();
    }

    @Override
    public final ExpressionEvaluationContext createContext() {
        return new FakeExpressionEvaluationContext() {
            @Override
            public <T> Either<T, String> convert(final Object value, final Class<T> target) {
                if (value instanceof Boolean && Boolean.class == target) {
                    return this.successfulConversion(
                            value,
                            target
                    );
                }
                if (value instanceof String && Boolean.class == target) {
                    return this.successfulConversion(
                            Boolean.parseBoolean((String) value),
                            target
                    );
                }

                return this.failConversion(value, target);
            }
        };
    }

    @Override
    public final String typeNamePrefix() {
        return BooleanExpressionFunction.class.getSimpleName();
    }
}

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

import org.junit.jupiter.api.Test;
import walkingkooka.Cast;
import walkingkooka.Either;
import walkingkooka.reflect.ClassTesting2;
import walkingkooka.reflect.JavaVisibility;
import walkingkooka.reflect.TypeNameTesting;
import walkingkooka.tree.expression.ExpressionPurityContext;
import walkingkooka.tree.expression.ExpressionPurityTesting;
import walkingkooka.tree.expression.FunctionExpressionName;
import walkingkooka.tree.expression.function.ExpressionFunction;
import walkingkooka.tree.expression.function.ExpressionFunctionContext;
import walkingkooka.tree.expression.function.ExpressionFunctionTesting;
import walkingkooka.tree.expression.function.FakeExpressionFunctionContext;

import java.util.List;

public abstract class BooleanExpressionFunctionTestCase<F extends ExpressionFunction<T, ExpressionFunctionContext>, T> implements ExpressionFunctionTesting<F, T, ExpressionFunctionContext>,
        ExpressionPurityTesting,
        TypeNameTesting<F>,
        ClassTesting2<F> {

    BooleanExpressionFunctionTestCase() {
        super();
    }

    @Test
    public final void testIsPureTrue() {
        this.isPureAndCheck(
                this.createBiFunction(),
                new ExpressionPurityContext() {
                    @Override
                    public boolean isPure(final FunctionExpressionName name) {
                        throw new UnsupportedOperationException();
                    }
                },
                true
        );
    }


    final void apply2(final Object... parameters) {
        this.createBiFunction().apply(parameters(parameters), this.createContext());
    }

    final void applyAndCheck2(final List<Object> parameters,
                              final T result) {
        this.applyAndCheck2(this.createBiFunction(), parameters, result);
    }

    final void applyAndCheck2(final ExpressionFunction<T, ExpressionFunctionContext> function,
                              final List<Object> parameters,
                              final T result) {
        this.applyAndCheck2(function, parameters, this.createContext(), result);
    }

    @Override
    public ExpressionFunctionContext createContext() {
        return new FakeExpressionFunctionContext() {
            @Override
            public <T> Either<T, String> convert(final Object value, final Class<T> target) {
                if(value instanceof Boolean && Boolean.class == target) {
                    return Cast.to(
                            Either.left(
                                    value
                            )
                    );
                }
                if(value instanceof String && Boolean.class == target) {
                    return Cast.to(
                            Either.left(
                                    Boolean.parseBoolean((String) value)
                            )
                    );
                }

                return this.failConversion(value, target);
            }
        };
    }

    @Override
    public final JavaVisibility typeVisibility() {
        return JavaVisibility.PACKAGE_PRIVATE;
    }

    @Override
    public final String typeNamePrefix() {
        return BooleanExpressionFunction.class.getSimpleName();
    }

    @Override
    public final String typeNameSuffix() {
        return "";
    }
}

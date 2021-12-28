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

import walkingkooka.collect.list.Lists;
import walkingkooka.reflect.PublicStaticHelper;
import walkingkooka.tree.expression.function.ExpressionFunction;
import walkingkooka.tree.expression.function.ExpressionFunctionContext;

import java.util.function.Consumer;

/**
 * Collection of static factory methods for numerous {@link ExpressionFunction}.
 */
public final class BooleanExpressionFunctions implements PublicStaticHelper {

    /**
     * Visit all {@link ExpressionFunction functions}.
     */
    public static void visit(final Consumer<ExpressionFunction<?, ?>> consumer) {
        Lists.of(
                booleanFunction(),
                equals(),
                falseFunction(),
                greaterThan(),
                greaterThanEquals(),
                lessThan(),
                lessThanEquals(),
                not(),
                notEquals(),
                trueFunction()
        ).forEach(consumer);
    }

    /**
     * {@see BooleanExpressionFunction}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<Boolean, C> booleanFunction() {
        return BooleanExpressionFunction.instance();
    }

    /**
     * {@see ComparisonExpressionFunction}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<Boolean, C> equals() {
        return ComparisonExpressionFunction.equals();
    }

    /**
     * {@see FalseExpressionFunction}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<Boolean, C> falseFunction() {
        return FalseExpressionFunction.instance();
    }

    /**
     * {@see ComparisonExpressionFunction}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<Boolean, C> greaterThan() {
        return ComparisonExpressionFunction.greaterThan();
    }

    /**
     * {@see ComparisonExpressionFunction}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<Boolean, C> greaterThanEquals() {
        return ComparisonExpressionFunction.greaterThanEqual();
    }

    /**
     * {@see ComparisonExpressionFunction}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<Boolean, C> lessThan() {
        return ComparisonExpressionFunction.lessThan();
    }

    /**
     * {@see ComparisonExpressionFunction}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<Boolean, C> lessThanEquals() {
        return ComparisonExpressionFunction.lessThanEqual();
    }

    /**
     * {@see NotExpressionFunction}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<Boolean, C> not() {
        return NotExpressionFunction.instance();
    }

    /**
     * {@see ComparisonExpressionFunction}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<Boolean, C> notEquals() {
        return ComparisonExpressionFunction.notEquals();
    }

    /**
     * {@see TrueExpressionFunction}
     */
    public static <C extends ExpressionFunctionContext> ExpressionFunction<Boolean, C> trueFunction() {
        return TrueExpressionFunction.instance();
    }

    /**
     * Stops creation
     */
    private BooleanExpressionFunctions() {
        throw new UnsupportedOperationException();
    }
}

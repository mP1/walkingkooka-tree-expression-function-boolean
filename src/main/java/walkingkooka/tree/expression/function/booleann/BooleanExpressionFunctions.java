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
import walkingkooka.tree.expression.ExpressionEvaluationContext;
import walkingkooka.tree.expression.function.ExpressionFunction;

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
                and(),
                booleanFunction(),
                choose(),
                equals(),
                falseFunction(),
                greaterThan(),
                greaterThanEquals(),
                ifFunction(),
                ifs(),
                isBoolean(),
                isReference(),
                lessThan(),
                lessThanEquals(),
                not(),
                notEquals(),
                or(),
                switchFunction(),
                trueFunction(),
                xor()
        ).forEach(consumer);
    }

    /**
     * {@see BooleanExpressionFunctionLogicalAnd}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<Boolean, C> and() {
        return BooleanExpressionFunctionLogicalAnd.instance();
    }

    /**
     * {@see BooleanExpressionFunctionTo}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<Boolean, C> booleanFunction() {
        return BooleanExpressionFunctionTo.instance();
    }

    /**
     * {@see ObjectExpressionFunctionChoose}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<Object, C> choose() {
        return ObjectExpressionFunctionChoose.instance();
    }

    /**
     * {@see BooleanExpressionFunctionComparison}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<Boolean, C> equals() {
        return BooleanExpressionFunctionComparison.equals();
    }

    /**
     * {@see BooleanExpressionFunctionFalse}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<Boolean, C> falseFunction() {
        return BooleanExpressionFunctionFalse.instance();
    }

    /**
     * {@see BooleanExpressionFunctionComparison}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<Boolean, C> greaterThan() {
        return BooleanExpressionFunctionComparison.greaterThan();
    }

    /**
     * {@see BooleanExpressionFunctionComparison}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<Boolean, C> greaterThanEquals() {
        return BooleanExpressionFunctionComparison.greaterThanEqual();
    }

    /**
     * {@see ObjectExpressionFunctionIf}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<Object, C> ifFunction() {
        return ObjectExpressionFunctionIf.instance();
    }

    /**
     * {@see ObjectExpressionFunctionIfs}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<Object, C> ifs() {
        return ObjectExpressionFunctionIfs.instance();
    }

    /**
     * {@see BooleanExpressionFunctionIsBoolean}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<Boolean, C> isBoolean() {
        return BooleanExpressionFunctionIsBoolean.instance();
    }

    /**
     * {@see BooleanExpressionFunctionIsReference}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<Boolean, C> isReference() {
        return BooleanExpressionFunctionIsReference.instance();
    }

    /**
     * {@see BooleanExpressionFunctionComparison}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<Boolean, C> lessThan() {
        return BooleanExpressionFunctionComparison.lessThan();
    }

    /**
     * {@see BooleanExpressionFunctionComparison}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<Boolean, C> lessThanEquals() {
        return BooleanExpressionFunctionComparison.lessThanEqual();
    }

    /**
     * {@see BooleanExpressionFunctionNot}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<Boolean, C> not() {
        return BooleanExpressionFunctionNot.instance();
    }

    /**
     * {@see BooleanExpressionFunctionComparison}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<Boolean, C> notEquals() {
        return BooleanExpressionFunctionComparison.notEquals();
    }

    /**
     * {@see BooleanExpressionFunctionLogicalOr}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<Boolean, C> or() {
        return BooleanExpressionFunctionLogicalOr.instance();
    }

    /**
     * {@see ObjectExpressionFunctionSwitch}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<Object, C> switchFunction() {
        return ObjectExpressionFunctionSwitch.instance();
    }

    /**
     * {@see BooleanExpressionFunctionTrue}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<Boolean, C> trueFunction() {
        return BooleanExpressionFunctionTrue.instance();
    }

    /**
     * {@see BooleanExpressionFunctionLogicalXor}
     */
    public static <C extends ExpressionEvaluationContext> ExpressionFunction<Boolean, C> xor() {
        return BooleanExpressionFunctionLogicalXor.instance();
    }

    /**
     * Stops creation
     */
    private BooleanExpressionFunctions() {
        throw new UnsupportedOperationException();
    }
}

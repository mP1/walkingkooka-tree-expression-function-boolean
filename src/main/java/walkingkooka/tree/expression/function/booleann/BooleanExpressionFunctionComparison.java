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
import walkingkooka.compare.ComparisonRelation;
import walkingkooka.tree.expression.FunctionExpressionName;
import walkingkooka.tree.expression.function.ExpressionFunctionContext;
import walkingkooka.tree.expression.function.ExpressionFunctionParameter;
import walkingkooka.tree.expression.function.ExpressionFunctionParameterName;

import java.util.List;

/**
 * A function that compares two parameters of the same value. Before comparing the second value is coverted to the same
 * type as the first.
 */
final class BooleanExpressionFunctionComparison<C extends ExpressionFunctionContext> extends BooleanExpressionFunction<C> {

    static <C extends ExpressionFunctionContext> BooleanExpressionFunctionComparison<C> equals() {
        return Cast.to(EQ);
    }

    private final static BooleanExpressionFunctionComparison<ExpressionFunctionContext> EQ = new BooleanExpressionFunctionComparison<ExpressionFunctionContext>("equals", ComparisonRelation.EQ);

    static <C extends ExpressionFunctionContext> BooleanExpressionFunctionComparison<C> notEquals() {
        return Cast.to(NE);
    }

    private final static BooleanExpressionFunctionComparison<ExpressionFunctionContext> NE = new BooleanExpressionFunctionComparison<ExpressionFunctionContext>("not-equals", ComparisonRelation.NE);

    static <C extends ExpressionFunctionContext> BooleanExpressionFunctionComparison<C> greaterThan() {
        return Cast.to(GT);
    }

    private final static BooleanExpressionFunctionComparison<ExpressionFunctionContext> GT = new BooleanExpressionFunctionComparison<ExpressionFunctionContext>("greater-than", ComparisonRelation.GT);

    static <C extends ExpressionFunctionContext> BooleanExpressionFunctionComparison<C> greaterThanEqual() {
        return Cast.to(GTE);
    }

    private final static BooleanExpressionFunctionComparison<ExpressionFunctionContext> GTE = new BooleanExpressionFunctionComparison<ExpressionFunctionContext>("greater-than-equal", ComparisonRelation.GTE);

    static <C extends ExpressionFunctionContext> BooleanExpressionFunctionComparison<C> lessThan() {
        return Cast.to(LT);
    }

    private final static BooleanExpressionFunctionComparison<ExpressionFunctionContext> LT = new BooleanExpressionFunctionComparison<ExpressionFunctionContext>("less-than", ComparisonRelation.LT);

    static <C extends ExpressionFunctionContext> BooleanExpressionFunctionComparison<C> lessThanEqual() {
        return Cast.to(LTE);
    }

    private final static BooleanExpressionFunctionComparison<ExpressionFunctionContext> LTE = new BooleanExpressionFunctionComparison<ExpressionFunctionContext>("less-than-equal", ComparisonRelation.LTE);

    /**
     * Private ctor
     */
    private BooleanExpressionFunctionComparison(final String name, final ComparisonRelation relation) {
        super();
        this.name = FunctionExpressionName.with(name);
        this.relation = relation;
    }

    @Override
    public Boolean apply(final List<Object> parameters,
                         final C context) {
        this.checkOnlyRequiredParameters(parameters);

        final Comparable<?> first = FIRST.getOrFail(parameters, 0);
        final Comparable<?> second = SECOND.getOrFail(parameters, 1);

        return this.relation.predicate(
                Cast.to(second)).test(Cast.to(first)
        );
    }

    private final ComparisonRelation relation;

    @Override
    public FunctionExpressionName name() {
        return name;
    }

    private final FunctionExpressionName name;

    @Override
    public List<ExpressionFunctionParameter<?>> parameters() {
        return PARAMETERS;
    }

    private final static ExpressionFunctionParameter<Comparable> FIRST = ExpressionFunctionParameterName.with("first")
            .setType(Comparable.class);

    private final static ExpressionFunctionParameter<Comparable> SECOND = ExpressionFunctionParameterName.with("second")
            .setType(Comparable.class);

    private final static List<ExpressionFunctionParameter<?>> PARAMETERS = ExpressionFunctionParameter.list(FIRST, SECOND);
}

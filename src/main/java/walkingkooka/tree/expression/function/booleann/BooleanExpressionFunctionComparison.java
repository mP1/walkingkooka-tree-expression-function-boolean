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
import walkingkooka.compare.CompareResult;
import walkingkooka.tree.expression.ExpressionEvaluationContext;
import walkingkooka.tree.expression.function.ExpressionFunctionParameter;
import walkingkooka.tree.expression.function.ExpressionFunctionParameterKind;
import walkingkooka.tree.expression.function.ExpressionFunctionParameterName;

import java.util.List;

/**
 * A function that compares two parameters of the same value. Before comparing the second value is coverted to the same
 * type as the first.
 */
final class BooleanExpressionFunctionComparison<C extends ExpressionEvaluationContext> extends BooleanExpressionFunction<C> {

    static <C extends ExpressionEvaluationContext> BooleanExpressionFunctionComparison<C> equals() {
        return Cast.to(EQ);
    }

    private final static BooleanExpressionFunctionComparison<ExpressionEvaluationContext> EQ = new BooleanExpressionFunctionComparison<ExpressionEvaluationContext>(
            "equals",
            CompareResult.EQ
    );

    static <C extends ExpressionEvaluationContext> BooleanExpressionFunctionComparison<C> notEquals() {
        return Cast.to(NE);
    }

    private final static BooleanExpressionFunctionComparison<ExpressionEvaluationContext> NE = new BooleanExpressionFunctionComparison<ExpressionEvaluationContext>(
            "not-equals",
            CompareResult.NE
    );

    static <C extends ExpressionEvaluationContext> BooleanExpressionFunctionComparison<C> greaterThan() {
        return Cast.to(GT);
    }

    private final static BooleanExpressionFunctionComparison<ExpressionEvaluationContext> GT = new BooleanExpressionFunctionComparison<ExpressionEvaluationContext>(
            "greater-than",
            CompareResult.GT
    );

    static <C extends ExpressionEvaluationContext> BooleanExpressionFunctionComparison<C> greaterThanEqual() {
        return Cast.to(GTE);
    }

    private final static BooleanExpressionFunctionComparison<ExpressionEvaluationContext> GTE = new BooleanExpressionFunctionComparison<ExpressionEvaluationContext>(
            "greater-than-equal",
            CompareResult.GTE
    );

    static <C extends ExpressionEvaluationContext> BooleanExpressionFunctionComparison<C> lessThan() {
        return Cast.to(LT);
    }

    private final static BooleanExpressionFunctionComparison<ExpressionEvaluationContext> LT = new BooleanExpressionFunctionComparison<ExpressionEvaluationContext>(
            "less-than",
            CompareResult.LT
    );

    static <C extends ExpressionEvaluationContext> BooleanExpressionFunctionComparison<C> lessThanEqual() {
        return Cast.to(LTE);
    }

    private final static BooleanExpressionFunctionComparison<ExpressionEvaluationContext> LTE = new BooleanExpressionFunctionComparison<ExpressionEvaluationContext>(
            "less-than-equal",
            CompareResult.LTE
    );

    /**
     * Private ctor
     */
    private BooleanExpressionFunctionComparison(final String name, final CompareResult relation) {
        super(name);
        this.relation = relation;
    }

    @Override
    public Boolean apply(final List<Object> parameters,
                         final C context) {
        this.checkParameterCount(parameters);

        final Comparable<?> first = FIRST.getOrFail(parameters, 0);
        final Comparable<?> second = SECOND.getOrFail(parameters, 1);

        return this.relation.predicate(
                Cast.to(second)).test(Cast.to(first)
        );
    }

    private final CompareResult relation;

    @Override
    public List<ExpressionFunctionParameter<?>> parameters(final int count) {
        return PARAMETERS;
    }

    private final static ExpressionFunctionParameter<Comparable> FIRST = ExpressionFunctionParameterName.with("first")
            .required(Comparable.class)
            .setKinds(ExpressionFunctionParameterKind.CONVERT_EVALUATE_RESOLVE_REFERENCES);

    private final static ExpressionFunctionParameter<Comparable> SECOND = ExpressionFunctionParameterName.with("second")
            .required(Comparable.class)
            .setKinds(ExpressionFunctionParameterKind.CONVERT_EVALUATE_RESOLVE_REFERENCES);

    private final static List<ExpressionFunctionParameter<?>> PARAMETERS = ExpressionFunctionParameter.list(FIRST, SECOND);
}

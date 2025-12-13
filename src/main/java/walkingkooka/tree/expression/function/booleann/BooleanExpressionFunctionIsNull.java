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
import walkingkooka.collect.set.Sets;
import walkingkooka.tree.expression.ExpressionEvaluationContext;
import walkingkooka.tree.expression.function.ExpressionFunctionParameter;
import walkingkooka.tree.expression.function.ExpressionFunctionParameterKind;

import java.util.List;

/**
 * A function that tests if the value given is <code>null</code>.
 */
final class BooleanExpressionFunctionIsNull<C extends ExpressionEvaluationContext> extends BooleanExpressionFunction<C> {

    /**
     * Instance getter.
     */
    static <C extends ExpressionEvaluationContext> BooleanExpressionFunctionIsNull<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private static final BooleanExpressionFunctionIsNull INSTANCE = new BooleanExpressionFunctionIsNull();

    /**
     * Private ctor
     */
    private BooleanExpressionFunctionIsNull() {
        super("isNull");
    }

    @Override
    public Boolean apply(final List<Object> parameters,
                         final C context) {
        this.checkParameterCount(parameters);

        return null == VALUE.getOrFail(parameters, 0, context);
    }

    private final static ExpressionFunctionParameter<Object> VALUE = ExpressionFunctionParameter.VALUE.setKinds(
            Sets.of(ExpressionFunctionParameterKind.EVALUATE)
    );

    @Override
    public List<ExpressionFunctionParameter<?>> parameters(final int count) {
        return PARAMETERS;
    }

    private final static List<ExpressionFunctionParameter<?>> PARAMETERS = ExpressionFunctionParameter.list(
            VALUE
    );
}

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
import walkingkooka.tree.expression.ExpressionPurityContext;
import walkingkooka.tree.expression.FunctionExpressionName;
import walkingkooka.tree.expression.function.ExpressionFunction;
import walkingkooka.tree.expression.function.ExpressionFunctionContext;
import walkingkooka.tree.expression.function.ExpressionFunctionParameter;
import walkingkooka.tree.expression.function.ExpressionFunctionParameterName;

import java.util.List;

/**
 * A function that inverts the a boolean value.
 */
final class NotExpressionFunction<C extends ExpressionFunctionContext> implements ExpressionFunction<Boolean, C> {

    /**
     * Instance getter.
     */
    static <C extends ExpressionFunctionContext> NotExpressionFunction<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private static final NotExpressionFunction INSTANCE = new NotExpressionFunction();

    /**
     * Private ctor
     */
    private NotExpressionFunction() {
        super();
    }

    @Override
    public Boolean apply(final List<Object> parameters,
                         final C context) {
        this.checkOnlyRequiredParameters(parameters);

        return !PARAMETER.getOrFail(parameters, 0);
    }

    @Override
    public FunctionExpressionName name() {
        return NAME;
    }

    private final static FunctionExpressionName NAME = FunctionExpressionName.with("not");

    @Override
    public List<ExpressionFunctionParameter<?>> parameters() {
        return PARAMETERS;
    }

    private final static ExpressionFunctionParameter<Boolean> PARAMETER = ExpressionFunctionParameterName.with("parameter")
            .setType(Boolean.class);

    private final static List<ExpressionFunctionParameter<?>> PARAMETERS = ExpressionFunctionParameter.list(
            PARAMETER
    );

    @Override
    public boolean lsLastParameterVariable() {
        return false;
    }

    @Override
    public Class<Boolean> returnType() {
        return Boolean.class;
    }

    @Override
    public boolean resolveReferences() {
        return false;
    }

    @Override
    public boolean isPure(final ExpressionPurityContext context) {
        return true;
    }

    @Override
    public String toString() {
        return this.name().toString();
    }
}

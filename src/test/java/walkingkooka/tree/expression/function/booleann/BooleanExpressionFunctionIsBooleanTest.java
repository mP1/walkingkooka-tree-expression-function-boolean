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
import walkingkooka.tree.expression.ExpressionEvaluationContext;
import walkingkooka.tree.expression.ExpressionNumberKind;

public final class BooleanExpressionFunctionIsBooleanTest extends BooleanExpressionFunctionTestCase<BooleanExpressionFunctionIsBoolean<ExpressionEvaluationContext>> {

    @Test
    public void testNullParameterFalse() {
        this.isBooleanAndCheck(
                null,
                false
        );
    }

    @Test
    public void testStringParameterFalse() {
        this.isBooleanAndCheck(
                "text123",
                false
        );
    }

    @Test
    public void testNumberZeroParameterFalse() {
        this.isBooleanAndCheck(
                ExpressionNumberKind.BIG_DECIMAL.zero(),
                false
        );
    }

    @Test
    public void testStringBooleanFalseFalse() {
        this.isBooleanAndCheck(
                false,
                true
        );
    }

    @Test
    public void testStringBooleanTrueFalse() {
        this.isBooleanAndCheck(
                true,
                true
        );
    }

    private void isBooleanAndCheck(final Object value, final boolean expected) {
        this.applyAndCheck2(
                parameters(value),
                expected
        );
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(this.createBiFunction(), "isBoolean");
    }

    @Override
    public BooleanExpressionFunctionIsBoolean<ExpressionEvaluationContext> createBiFunction() {
        return BooleanExpressionFunctionIsBoolean.instance();
    }

    @Override
    public Class<BooleanExpressionFunctionIsBoolean<ExpressionEvaluationContext>> type() {
        return Cast.to(BooleanExpressionFunctionIsBoolean.class);
    }
}

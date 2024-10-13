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
import walkingkooka.tree.expression.ExpressionReference;
import walkingkooka.tree.expression.function.ExpressionFunctionParameterName;

public final class BooleanExpressionFunctionIsNullTest extends BooleanExpressionFunctionTestCase<BooleanExpressionFunctionIsNull<ExpressionEvaluationContext>> {

    @Test
    public void testNullParameterTrue() {
        this.isBooleanAndCheck(
                null,
                true
        );
    }

    @Test
    public void testBooleanFalseParameterFalse() {
        this.isBooleanAndCheck(
                false,
                false
        );
    }

    @Test
    public void testBooleanTrueParameterFalse() {
        this.isBooleanAndCheck(
                false,
                false
        );
    }

    @Test
    public void testNumberParameterFalse() {
        this.isBooleanAndCheck(
                123,
                false
        );
    }

    @Test
    public void testReferenceParameterFalse() {
        this.isBooleanAndCheck(
                new ExpressionReference() {
                    @Override
                    public boolean testParameterName(final ExpressionFunctionParameterName name) {
                        throw new UnsupportedOperationException();
                    }
                },
                false
        );
    }

    @Test
    public void testStringParameterFalse() {
        this.isBooleanAndCheck(
                "Hello123",
                false
        );
    }

    private void isBooleanAndCheck(final Object value,
                                   final boolean expected) {
        this.applyAndCheck2(
                parameters(value),
                expected
        );
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(this.createBiFunction(), "isNull");
    }

    @Override
    public BooleanExpressionFunctionIsNull<ExpressionEvaluationContext> createBiFunction() {
        return BooleanExpressionFunctionIsNull.instance();
    }

    @Override
    public int minimumParameterCount() {
        return 0;
    }

    @Override
    public Class<BooleanExpressionFunctionIsNull<ExpressionEvaluationContext>> type() {
        return Cast.to(BooleanExpressionFunctionIsNull.class);
    }
}

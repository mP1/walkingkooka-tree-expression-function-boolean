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
import walkingkooka.tree.expression.ExpressionNumberKind;
import walkingkooka.tree.expression.ExpressionReference;
import walkingkooka.tree.expression.function.ExpressionFunctionContext;

public final class BooleanExpressionFunctionIsReferenceTest extends BooleanExpressionFunctionTestCase<BooleanExpressionFunctionIsReference<ExpressionFunctionContext>> {

    @Test
    public void testNullParameterFalse() {
        this.isReferenceAndCheck(
                null,
                false
        );
    }

    @Test
    public void testStringParameterFalse() {
        this.isReferenceAndCheck(
                "text123",
                false
        );
    }

    @Test
    public void testNumberZeroParameterFalse() {
        this.isReferenceAndCheck(
                ExpressionNumberKind.BIG_DECIMAL.zero(),
                false
        );
    }

    @Test
    public void testBooleanFalse() {
        this.isReferenceAndCheck(
                false,
                false
        );
    }

    @Test
    public void testReferenceTrue() {
        this.isReferenceAndCheck(
                new ExpressionReference() {
                },
                true
        );
    }

    private void isReferenceAndCheck(final Object value, final boolean expected) {
        this.applyAndCheck2(
                parameters(value),
                expected
        );
    }

    @Test
    public void testResolveReferencesFalse() {
        this.resolveReferencesAndCheck(false);
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(this.createBiFunction(), "isReference");
    }

    @Override
    public BooleanExpressionFunctionIsReference<ExpressionFunctionContext> createBiFunction() {
        return BooleanExpressionFunctionIsReference.instance();
    }

    @Override
    public Class<BooleanExpressionFunctionIsReference<ExpressionFunctionContext>> type() {
        return Cast.to(BooleanExpressionFunctionIsReference.class);
    }
}

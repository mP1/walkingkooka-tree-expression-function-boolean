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

import static org.junit.jupiter.api.Assertions.assertThrows;

public final class ObjectExpressionFunctionIfTest extends ObjectExpressionFunctionTestCase<ObjectExpressionFunctionIf<ExpressionEvaluationContext>> {

    @Test
    public void testZeroParametersFails() {
        assertThrows(IllegalArgumentException.class, this::apply2);
    }

    @Test
    public void testOneParametersFails() {
        assertThrows(IllegalArgumentException.class, () -> this.apply2("a1"));
    }

    @Test
    public void testTwoParametersFails() {
        assertThrows(IllegalArgumentException.class, () -> this.apply2("a1", 2));
    }

    @Test
    public void testBooleanTrue() {
        this.applyAndCheck2(parameters(true, "*1", "*2"), "*1");
    }

    @Test
    public void testBooleanFalse() {
        this.applyAndCheck2(parameters(false, "*1", "*2"), "*2");
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(this.createBiFunction(), "if");
    }

    public void testTypeNaming() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ObjectExpressionFunctionIf<ExpressionEvaluationContext> createBiFunction() {
        return ObjectExpressionFunctionIf.instance();
    }

    @Override
    public int minimumParameterCount() {
        return 3;
    }

    @Override
    public Class<ObjectExpressionFunctionIf<ExpressionEvaluationContext>> type() {
        return Cast.to(ObjectExpressionFunctionIf.class);
    }
}

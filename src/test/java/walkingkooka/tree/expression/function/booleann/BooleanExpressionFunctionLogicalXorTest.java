/*
 * Copyright 2020 Miroslav Pokorny (github.com/mP1)
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
import walkingkooka.collect.list.Lists;
import walkingkooka.tree.expression.ExpressionEvaluationContext;

public final class BooleanExpressionFunctionLogicalXorTest extends BooleanExpressionFunctionLogicalTestCase<BooleanExpressionFunctionLogicalXor<ExpressionEvaluationContext>> {

    @Test
    public void testTrue() {
        this.applyAndCheck2(
                Lists.of(true),
                true
        );
    }

    @Test
    public void testFalse() {
        this.applyAndCheck2(
                Lists.of(false),
                false
        );
    }

    @Test
    public void testTrueTrue() {
        this.applyAndCheck2(
                Lists.of(true, true),
                false
        );
    }

    @Test
    public void testFalseFalseFalse() {
        this.applyAndCheck2(
                Lists.of(false, false, false),
                false
        );
    }

    @Test
    public void testFalseFalseTrue() {
        this.applyAndCheck2(
                Lists.of(false, false, true),
                true
        );
    }

    @Test
    public void testTrueFalseTrue() {
        this.applyAndCheck2(
                Lists.of(true, false, true),
                false
        );
    }

    @Test
    public void testTrueTrueTrue() {
        this.applyAndCheck2(
                Lists.of(true, true, true),
                true
        );
    }

    @Override
    public BooleanExpressionFunctionLogicalXor<ExpressionEvaluationContext> createBiFunction() {
        return BooleanExpressionFunctionLogicalXor.instance();
    }

    @Override
    public Class<BooleanExpressionFunctionLogicalXor<ExpressionEvaluationContext>> type() {
        return Cast.to(BooleanExpressionFunctionLogicalXor.class);
    }
}

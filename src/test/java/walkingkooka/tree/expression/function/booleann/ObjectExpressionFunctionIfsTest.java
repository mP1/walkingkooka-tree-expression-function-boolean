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
import walkingkooka.collect.list.Lists;
import walkingkooka.tree.expression.function.ExpressionFunctionContext;

public final class ObjectExpressionFunctionIfsTest extends ObjectExpressionFunctionTestCase<ObjectExpressionFunctionIfs<ExpressionFunctionContext>> {

    @Test
    public void testZero() {
        this.applyAndCheck2(
                Lists.empty(),
                null
        );
    }

    @Test
    public void testFirstParameterBooleanTrue() {
        this.applyAndCheck2(
                Lists.of(
                        true,
                        123
                ),
                123
        );
    }

    @Test
    public void testFirstParameterBooleanFalse() {
        this.applyAndCheck2(
                Lists.of(
                        false,
                        123
                ),
                null
        );
    }

    @Test
    public void testThirdParameterBooleanTrue() {
        this.applyAndCheck2(
                Lists.of(
                        false,
                        111,
                        true,
                        222
                ),
                222
        );
    }

    @Test
    public void testThirdParameterBooleanTrue2() {
        this.applyAndCheck2(
                Lists.of(
                        false,
                        111,
                        true,
                        222,
                        false,
                        333
                ),
                222
        );
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(this.createBiFunction(), "ifs");
    }

    public void testTypeNaming() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ObjectExpressionFunctionIfs<ExpressionFunctionContext> createBiFunction() {
        return ObjectExpressionFunctionIfs.instance();
    }

    @Override
    public Class<ObjectExpressionFunctionIfs<ExpressionFunctionContext>> type() {
        return Cast.to(ObjectExpressionFunctionIfs.class);
    }
}

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
import walkingkooka.tree.expression.ExpressionEvaluationContext;
import walkingkooka.tree.expression.ExpressionNumberKind;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public final class ObjectExpressionFunctionChooseTest extends ObjectExpressionFunctionTestCase<ObjectExpressionFunctionChoose<ExpressionEvaluationContext>> {

    @Test
    public void testZeroParametersFails() {
        assertThrows(IllegalArgumentException.class, this::apply2);
    }

    private final static String SELECTED = "Selected";

    @Test
    public void testOne() {
        this.chooseAndCheck(
                1,
                Lists.of(SELECTED),
                SELECTED
        );
    }

    @Test
    public void testOne2() {
        this.chooseAndCheck(
                1,
                Lists.of(SELECTED, "Wrong"),
                SELECTED
        );
    }

    @Test
    public void testOneBoolean() {
        this.chooseAndCheck(
                1,
                Lists.of(true),
                true
        );
    }

    @Test
    public void testTwo() {
        this.chooseAndCheck(
                2,
                Lists.of("Wrong1", SELECTED, "Wrong2"),
                SELECTED
        );
    }

    @Test
    public void testThree() {
        this.chooseAndCheck(
                3,
                Lists.of("Wrong1", "Wrong2", SELECTED),
                SELECTED
        );
    }

    @Test
    public void testThree2() {
        this.chooseAndCheck(
                3,
                Lists.of(10, 20, 30),
                30
        );
    }

    private void chooseAndCheck(final int index,
                                final List<Object> values,
                                final Object expected) {
        final List<Object> parameters = Lists.array();
        parameters.add(ExpressionNumberKind.DEFAULT.create(index));
        parameters.addAll(values);

        this.applyAndCheck2(
                parameters,
                expected
        );
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(this.createBiFunction(), "choose");
    }

    public void testTypeNaming() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ObjectExpressionFunctionChoose<ExpressionEvaluationContext> createBiFunction() {
        return ObjectExpressionFunctionChoose.instance();
    }

    @Override
    public int minimumParameterCount() {
        return 1;
    }

    @Override
    public Class<ObjectExpressionFunctionChoose<ExpressionEvaluationContext>> type() {
        return Cast.to(ObjectExpressionFunctionChoose.class);
    }
}

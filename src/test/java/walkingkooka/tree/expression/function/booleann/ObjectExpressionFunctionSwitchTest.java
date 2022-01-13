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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public final class ObjectExpressionFunctionSwitchTest extends ObjectExpressionFunctionTestCase<ObjectExpressionFunctionSwitch<ExpressionFunctionContext>> {

    @Test
    public void testZeroParametersFails() {
        assertThrows(IllegalArgumentException.class, this::apply2);
    }

    private final static String TEST = "Test123";

    private final static String DEFAULT = "Default456";

    private final static String VALUE = "Value789";

    private final static String NOT_KEY = "Not!Key";

    private final static String NOT_VALUE = "Not!Value";

    @Test
    public void testOnlyDefault() {
        this.switchAndCheck(
                1,
                Lists.of(DEFAULT),
                DEFAULT
        );
    }

    @Test
    public void testKeyAndValue() {
        this.switchAndCheck(
                TEST,
                Lists.of(TEST, VALUE),
                VALUE
        );
    }

    @Test
    public void testKeyAndValue2() {
        this.switchAndCheck(
                TEST,
                Lists.of(NOT_KEY, NOT_VALUE, TEST, VALUE),
                VALUE
        );
    }

    @Test
    public void testKeyAndValue3() {
        this.switchAndCheck(
                TEST,
                Lists.of(NOT_KEY, NOT_VALUE, TEST, VALUE, NOT_KEY, NOT_VALUE),
                VALUE
        );
    }

    @Test
    public void testKeyAndValue4() {
        final int test = 123;

        this.switchAndCheck(
                test,
                Lists.of(NOT_KEY, NOT_VALUE, test, VALUE),
                VALUE
        );
    }

    @Test
    public void testKeyAndValueDefaultIgnored() {
        this.switchAndCheck(
                TEST,
                Lists.of(NOT_KEY, NOT_VALUE, TEST, VALUE, DEFAULT),
                VALUE
        );
    }

    @Test
    public void testKeyAndValueDefaultIgnored2() {
        this.switchAndCheck(
                TEST,
                Lists.of(NOT_KEY, NOT_VALUE, NOT_KEY, NOT_VALUE, TEST, VALUE, DEFAULT),
                VALUE
        );
    }

    @Test
    public void testKeyAndValueDefaulted() {
        this.switchAndCheck(
                1,
                Lists.of(NOT_KEY, NOT_VALUE, DEFAULT),
                DEFAULT
        );
    }

    @Test
    public void testKeyAndValueDefaulted2() {
        this.switchAndCheck(
                1,
                Lists.of(NOT_KEY, NOT_VALUE, NOT_KEY, NOT_VALUE, DEFAULT),
                DEFAULT
        );
    }

    private void switchAndCheck(final Object test,
                                final List<Object> keyValues,
                                final Object expected) {
        final List<Object> parameters = Lists.array();

        parameters.add(test);
        parameters.addAll(keyValues);

        this.applyAndCheck2(
                parameters,
                expected
        );
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(this.createBiFunction(), "switch");
    }

    public void testTypeNaming() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ObjectExpressionFunctionSwitch<ExpressionFunctionContext> createBiFunction() {
        return ObjectExpressionFunctionSwitch.instance();
    }

    @Override
    public Class<ObjectExpressionFunctionSwitch<ExpressionFunctionContext>> type() {
        return Cast.to(ObjectExpressionFunctionSwitch.class);
    }
}

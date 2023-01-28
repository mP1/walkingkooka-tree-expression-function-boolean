package test;

import com.google.gwt.junit.client.GWTTestCase;

import walkingkooka.collect.list.Lists;
import walkingkooka.tree.expression.ExpressionEvaluationContexts;
import walkingkooka.tree.expression.function.booleann.BooleanExpressionFunctions;

public class TestGwtTest extends GWTTestCase {

    @Override
    public String getModuleName() {
        return "test.Test";
    }

    public void testAssertEquals() {
        assertEquals(
                1,
                1
        );
    }

    public void testTrue() {
        assertEquals(
                Boolean.TRUE,
                BooleanExpressionFunctions.trueFunction()
                        .apply(
                                Lists.of(),
                                ExpressionEvaluationContexts.fake()
                        )
        );
    }
}

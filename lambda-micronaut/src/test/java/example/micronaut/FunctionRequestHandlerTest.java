package example.micronaut;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class FunctionRequestHandlerTest {

    private static FunctionRequestHandler handler;

    @BeforeAll
    public static void setupServer() {
        handler = new FunctionRequestHandler();
    }

    @AfterAll
    public static void stopServer() {
        if (handler != null) {
            handler.getApplicationContext().close();
        }
    }

    @Test
    public void testHandler() {
        // given
        final InputObject request = new InputObject().setName("John Doe");

        // when
        final OutputObject result = handler.execute(request);

        // then
        assertTrue(result.getMessage().startsWith("Hello John Doe! My name is"));
    }
}

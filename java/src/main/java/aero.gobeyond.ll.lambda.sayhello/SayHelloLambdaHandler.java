package aero.gobeyond.ll.lambda.sayhello;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class SayHelloLambdaHandler implements RequestHandler<Map<String, String>, String> {
  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
  private static final URL RANDOM_NAME_API;

  static {
    try {
      RANDOM_NAME_API = new URL("https://randomuser.me/api/?format=json");
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
  }

  public String handleRequest(Map<String, String> input, Context context) {

    try {
      final String sayHelloTo = input.get("name");

      final JsonNode name = OBJECT_MAPPER.readTree(RANDOM_NAME_API)
          .get("results")
          .get(0)
          .get("name");
      final String firstName = name.get("first").asText();
      final String lastName = name.get("last").asText();

      return String.format(
          "{ \"message\": \"Hello %s! My name is %s %s. Nice to meet you!\" }",
          sayHelloTo, firstName, lastName
      );
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}

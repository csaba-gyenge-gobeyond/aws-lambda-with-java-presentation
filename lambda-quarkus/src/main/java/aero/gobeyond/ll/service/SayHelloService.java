package aero.gobeyond.ll.service;

import static java.lang.String.format;

import aero.gobeyond.ll.lambda.OutputObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SayHelloService {
  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  private static final URL RANDOM_NAME_API;

  static {
    try {
      RANDOM_NAME_API = new URL("https://randomuser.me/api/?format=json");
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
  }

  public OutputObject sayHello(String to) {
    try {
      final JsonNode name = OBJECT_MAPPER.readTree(RANDOM_NAME_API)
          .get("results")
          .get(0)
          .get("name");
      final String firstName = name.get("first").asText();
      final String lastName = name.get("last").asText();
      return new OutputObject()
          .setMessage(
              format("Hello %s! My name is %s %s. Nice to meet you!", to, firstName, lastName)
          );
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}

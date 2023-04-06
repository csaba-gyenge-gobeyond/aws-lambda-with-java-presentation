package example.micronaut;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class OutputObject {

  private String message;

  public String getMessage() {
    return message;
  }

  public OutputObject setMessage(String message) {
    this.message = message;
    return this;
  }
}

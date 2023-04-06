package example.micronaut;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class InputObject {

  private String name;

  public String getName() {
    return name;
  }

  public InputObject setName(String name) {
    this.name = name;
    return this;
  }
}

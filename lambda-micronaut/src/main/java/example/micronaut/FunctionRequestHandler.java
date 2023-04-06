package example.micronaut;

import example.micronaut.service.SayHelloService;
import io.micronaut.function.aws.MicronautRequestHandler;
import jakarta.inject.Inject;

public class FunctionRequestHandler extends MicronautRequestHandler<InputObject, OutputObject> {
  @Inject
  SayHelloService sayHelloService;

  @Override
  public OutputObject execute(InputObject input) {
    return sayHelloService.sayHello(input.getName());
  }
}

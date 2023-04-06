package example.micronaut;

import com.amazonaws.services.lambda.runtime.RequestHandler;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.function.aws.runtime.AbstractMicronautLambdaRuntime;
import java.net.MalformedURLException;

public class FunctionLambdaRuntime extends AbstractMicronautLambdaRuntime<InputObject, OutputObject, InputObject, OutputObject> {
  public static void main(String[] args) {
    try {
      new FunctionLambdaRuntime().run(args);
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
  }

  @Override
  @Nullable
  protected RequestHandler<InputObject, OutputObject> createRequestHandler(String... args) {
    return new FunctionRequestHandler();
  }
}

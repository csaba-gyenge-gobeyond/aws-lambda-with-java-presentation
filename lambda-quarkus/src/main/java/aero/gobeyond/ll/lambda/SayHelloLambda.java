package aero.gobeyond.ll.lambda;

import aero.gobeyond.ll.service.SayHelloService;
import javax.inject.Named;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

@Named("sayHello")
public class SayHelloLambda implements RequestHandler<InputObject, OutputObject> {
    private SayHelloService sayHelloService;

    public SayHelloLambda(SayHelloService sayHelloService) {
        this.sayHelloService = sayHelloService;
    }

    @Override
    public OutputObject handleRequest(InputObject input, Context context) {
        return sayHelloService.sayHello(input.getName());
    }
}

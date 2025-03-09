package com.baiji.ecust;

public interface Greeter {

        /**
        * sayHello method.
        * @param request the HelloRequest request object.
        * @return the HelloResponse response object.
        */
        HelloResponse sayHello(HelloRequest request) throw Exception;
        /**
        * sayHello2 method.
        * @param request the Hello2Request request object.
        * @return the Hello2Response response object.
        */
        Hello2Response sayHello2(Hello2Request request) throw Exception;
}

package com.pedrolsoares.grpcserver;

import com.pedrolsoares.grpcserver.hello.HelloRequest;
import com.pedrolsoares.grpcserver.hello.HelloResponse;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import com.pedrolsoares.grpcserver.hello.HelloServiceGrpc;

@GRpcService
public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase{

    @Override
    public void hello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {

        String greeting = "Hello, " +
                request.getFirstName() +
                " " +
                request.getLastName();
        System.out.println(greeting);

        HelloResponse response = HelloResponse.newBuilder()
                .setGreeting(greeting)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }
}

package com.pedrolsoares.grpcclient;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import com.pedrolsoares.grpcclient.hello.HelloRequest;
import com.pedrolsoares.grpcclient.hello.HelloResponse;
import com.pedrolsoares.grpcclient.hello.HelloServiceGrpc;
import io.grpc.StatusRuntimeException;

import java.util.concurrent.TimeUnit;

public class HelloClient {

    public static void main(String[] args) throws InterruptedException {
        final ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 6565)
                .usePlaintext()
                .build();

        HelloRequest request = HelloRequest.newBuilder().setFirstName("Pedro").setLastName("Levada").build();
        HelloResponse response;

        try {
            response = HelloServiceGrpc.newBlockingStub(channel).hello(request);
            System.out.println(response.getGreeting());
        }catch (StatusRuntimeException e) {
            return;
        }finally {
            channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        }
    }
}

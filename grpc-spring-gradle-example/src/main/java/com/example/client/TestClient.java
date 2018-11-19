package com.example.client;

import com.example.hello.GreeterGrpc;
import com.example.hello.Hello.HelloReply;
import com.example.hello.Hello.HelloRequest;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class TestClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 final ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:10000")
			        .usePlaintext(true)
			        .build();

			      // It is up to the client to determine whether to block the call
			      // Here we create a blocking stub, but an async stub,
			      // or an async stub with Future are always possible.
			      GreeterGrpc.GreeterBlockingStub stub = GreeterGrpc.newBlockingStub(channel);
			      HelloRequest r = HelloRequest.newBuilder().setName("isuru").build();
			      HelloReply re = stub.sayHello(r);

			      // Finally, make the call using the stub
			      

			      System.out.println(re.getMessage());

			      // A Channel should be shutdown before stopping the process.
			      channel.shutdownNow();
	}

}

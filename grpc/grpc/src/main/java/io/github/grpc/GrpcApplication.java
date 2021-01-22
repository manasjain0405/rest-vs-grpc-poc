package io.github.grpc;

import io.github.grpc.service.SquareService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

public class GrpcApplication {

	public static void main(String[] args) throws IOException, InterruptedException {
		Server server = ServerBuilder.forPort(8082)
				.addService(new SquareService())
				.build();
			server.start();
			server.awaitTermination();
	}
}

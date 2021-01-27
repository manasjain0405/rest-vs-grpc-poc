package io.github.grpc;

import io.github.grpc.service.SquareService;
import io.github.grpc.service.StreamSquareService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GrpcApplication {

	public static void main(String[] args) throws IOException, InterruptedException {
		Server server = ServerBuilder.forPort(8082)
				.addService(new SquareService())
				.addService(new StreamSquareService())
				.build();
			server.start();
			server.awaitTermination();
	}
}

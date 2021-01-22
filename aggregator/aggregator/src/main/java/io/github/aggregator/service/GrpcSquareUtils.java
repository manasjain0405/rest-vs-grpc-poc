package io.github.aggregator.service;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.manasjain0405.grpc.*;
import org.springframework.stereotype.Service;

@Service
public class GrpcSquareUtils {

    private final SquareServiceGrpc.SquareServiceBlockingStub stub;

    public GrpcSquareUtils() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8082)
                .usePlaintext()
                .build();
        this.stub = SquareServiceGrpc.newBlockingStub(channel);
    }

    public Long getSquare(Long request){
        return stub.squareIt(SquareRequestResponse.newBuilder().setNumber(request).build()).getNumber();
    }

}

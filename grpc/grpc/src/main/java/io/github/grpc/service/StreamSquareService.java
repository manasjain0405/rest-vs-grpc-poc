package io.github.grpc.service;

import io.grpc.stub.StreamObserver;
import io.manasjain0405.grpc.SquareRequest;
import io.manasjain0405.grpc.SquareResponse;
import io.manasjain0405.grpc.StreamSquareServiceGrpc;

import java.util.LinkedHashSet;

public class StreamSquareService extends StreamSquareServiceGrpc.StreamSquareServiceImplBase {

    @Override
    public StreamObserver<SquareRequest> squareItStream(StreamObserver<SquareResponse> responseObserver) {
        return new StreamObserver<SquareRequest>() {
            @Override
            public void onNext(SquareRequest request) {
                //Receiving data from client to server
                final int requestNo = request.getNumber();
                final SquareResponse response = SquareResponse.newBuilder()
                        .setNumber(requestNo)
                        .setResult(requestNo*requestNo)
                        .build();
                responseObserver.onNext(response);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }
}

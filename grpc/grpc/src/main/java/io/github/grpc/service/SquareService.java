package io.github.grpc.service;

import io.grpc.stub.StreamObserver;
import io.manasjain0405.grpc.SquareRequest;
import io.manasjain0405.grpc.SquareResponse;
import io.manasjain0405.grpc.SquareServiceGrpc;

public class SquareService extends SquareServiceGrpc.SquareServiceImplBase {

    @Override
    public void squareIt(SquareRequest request, StreamObserver<SquareResponse> responseObserver) {
        final int no = request.getNumber();
        responseObserver.onNext(SquareResponse.newBuilder()
                .setNumber(no)
                .setResult(no*no)
                .build());
        responseObserver.onCompleted();
    }
}

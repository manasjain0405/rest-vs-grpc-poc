package io.github.grpc.service;

import io.grpc.stub.StreamObserver;
import io.manasjain0405.grpc.SquareRequestResponse;
import io.manasjain0405.grpc.SquareServiceGrpc;
import org.springframework.stereotype.Service;

public class SquareService extends SquareServiceGrpc.SquareServiceImplBase {

    @Override
    public void squareIt(SquareRequestResponse request, StreamObserver<SquareRequestResponse> responseObserver) {
        final Long no = request.getNumber();
        responseObserver.onNext(SquareRequestResponse.newBuilder().setNumber(no*no).build());
        responseObserver.onCompleted();
    }
}

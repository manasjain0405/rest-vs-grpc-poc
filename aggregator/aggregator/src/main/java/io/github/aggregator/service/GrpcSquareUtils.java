//package io.github.aggregator.service;
//
//import io.grpc.ManagedChannel;
//import io.grpc.ManagedChannelBuilder;
//import io.grpc.stub.StreamObserver;
//import io.manasjain0405.grpc.*;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.IntStream;
//import java.util.stream.LongStream;
//
//@Service
//public class GrpcSquareUtils {
//
//    private final SquareServiceGrpc.SquareServiceBlockingStub squareServiceBlockingStub;
//
//    private final StreamSquareServiceGrpc.StreamSquareServiceStub streamSquareServiceStub;
//
//    public GrpcSquareUtils() {
//        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8082)
//                .usePlaintext()
//                .build();
//        this.squareServiceBlockingStub = SquareServiceGrpc.newBlockingStub(channel);
//        this.streamSquareServiceStub = StreamSquareServiceGrpc.newStub(channel);
//    }
//
//    public SquareResponse getSquare(SquareRequest request){
//        return squareServiceBlockingStub.squareIt(request);
//    }
//
//    public Map<Integer, Integer> getSquareStream(Integer number) throws InterruptedException {
//        final Map<Integer, Integer> result = new HashMap<>();
//        final Boolean[] done = {false};
//        final StreamObserver<SquareRequest> toServer = streamSquareServiceStub.squareItStream(new StreamObserver<SquareResponse>() {
//            @Override
//            public void onNext(SquareResponse response) {
//                result.put(response.getNumber(), response.getResult());
//            }
//
//            @Override
//            public void onError(Throwable throwable) {
//
//            }
//
//            @Override
//            public void onCompleted() {
//                done[0] = true;
//            }
//        });
//
//        IntStream.range(1, number+1)
//                .mapToObj(num -> SquareRequest.newBuilder()
//                        .setNumber(num)
//                        .build())
//                .forEach( request -> {
//                    toServer.onNext(request);
//                });
//        toServer.onCompleted();
//        while (!done[0]){
//            Thread.sleep(100);
//        }
//        return result;
//    }
//}

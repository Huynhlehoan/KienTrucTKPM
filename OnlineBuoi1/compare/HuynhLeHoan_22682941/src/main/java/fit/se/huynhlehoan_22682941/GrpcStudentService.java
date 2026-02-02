package fit.se.huynhlehoan_22682941;

import fit.se.huynhlehoan_22682941.grpc.StudentRequest;
import fit.se.huynhlehoan_22682941.grpc.StudentResponse;
import fit.se.huynhlehoan_22682941.grpc.StudentServiceGrpc;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;


@GrpcService
public class GrpcStudentService extends StudentServiceGrpc.StudentServiceImplBase {

    @Override
    public void getStudent(StudentRequest request, StreamObserver<StudentResponse> responseObserver) {
        StudentResponse response = StudentResponse.newBuilder()
                .setId(request.getId())
                .setName("Tran Van C") // gRPC Demo
                .setAge(23)
                .setEmail("vanc@gmail.com")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}

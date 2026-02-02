package fit.se.huynhlehoan_22682941;

import fit.se.huynhlehoan_22682941.grpc.StudentRequest;
import fit.se.huynhlehoan_22682941.grpc.StudentResponse;
import fit.se.huynhlehoan_22682941.grpc.StudentServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class GrpcClientTest implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        // Tạo kênh kết nối đến Server gRPC (Port mặc định 9090)
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();

        StudentServiceGrpc.StudentServiceBlockingStub stub = StudentServiceGrpc.newBlockingStub(channel);

        // Gọi hàm
        StudentResponse response = stub.getStudent(StudentRequest.newBuilder().setId("123").build());

        System.out.println("--- DEMO gRPC CLIENT ---");
        System.out.println("Kết quả gRPC trả về: " + response.getName() + " - " + response.getEmail());
        System.out.println("------------------------");

        channel.shutdown();
    }
}
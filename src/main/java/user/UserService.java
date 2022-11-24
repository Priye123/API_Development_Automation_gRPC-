package user;

//1. Extend grpc java stubs
//2. get request inputs from client
//3. construct response using builder
//4. send back data using streamobserver.

import com.priye.grpc.User.APIResponse;
import com.priye.grpc.User.Empty;
import com.priye.grpc.User.LoginRequest;
import com.priye.grpc.userGrpc.userImplBase;

import io.grpc.stub.StreamObserver;

//we can test this API by using gRPC Server by attaching this UserService class into respective port
//@Service
public class UserService extends userImplBase {

	/*-----------------------IMPLEMENTATION OF API-------------------------------------*/

	@Override
	public void login(LoginRequest request, StreamObserver<APIResponse> responseObserver) {

		System.out.println("Inside login");

		String username = request.getUsername();
		String password = request.getPassword();

		// Builder means how u construct an object in gRPC
		// construct an object for response using builder
		APIResponse.Builder response = APIResponse.newBuilder();
		if (username.equals(password)) {

			// return success message
			response.setResponseCode(0).setResponsemessage("SUCCESS");

		} else {
			response.setResponseCode(400).setResponsemessage("INVALID PASSWORD");
		}

		responseObserver.onNext(response.build());// sending data back to the client
		responseObserver.onCompleted(); // close the call(connection b/w gRPC client & server for this respective API is
										// closed)
	}

	@Override
	public void logout(Empty request, StreamObserver<APIResponse> responseObserver) {
		System.out.println("Inside logout");
		APIResponse.Builder response = APIResponse.newBuilder();
		// return success message
		response.setResponseCode(0).setResponsemessage("SUCCESS");
		responseObserver.onNext(response.build());// sending data back to the client
		responseObserver.onCompleted();
	}

}
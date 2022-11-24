package gRPC_Cilent;

import com.priye.grpc.User.APIResponse;
import com.priye.grpc.User.Empty;
import com.priye.grpc.User.LoginRequest;
import com.priye.grpc.userGrpc;
import com.priye.grpc.userGrpc.userBlockingStub;

import exception.LoginNotFoundException;
import exception.LogoutNotFoundException;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

//how to implement a GRPC client in java. 
//Follow below steps to write a java program that communicates to GRPC server.
//1. Create a managed channel 
//2. Use stubs to call API or GRPC service
//3. Construct request and response objects using stubs

//@Service
public class GrpcClient {

//	public static void main(String[] args) {
//
//		// we have to create a channel that communicated with the gRPC server and
//		// later we can talk to our particular api.
//		// for that grpc provides a builder class to construct the channel
//		// we use forAddress() method to start server at that port
//
//		// Use of a plaintext connection to the server.
//		// By default a secure connection mechanism such as TLS will be used.
//		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090).usePlaintext().build();
//
//		// stubs - generate from proto(protoc compiler)
//		// we need stubs to call a particular API
//
//		// newBlockingStub:- client will wait for the server response and it will not
//		// proceed with further operations
//		userBlockingStub userStub = userGrpc.newBlockingStub(channel);
//
//		// use this stub(userStub) and call the api's
//
//		//login API
//		LoginRequest loginrequest = LoginRequest.newBuilder().setUsername("RAMasdf").setPassword("RAM").build();
//
//		APIResponse response = userStub.login(loginrequest);// calling the api's with that stubs
//
//		System.out.println(response.getResponsemessage());
//
//		//logout API
//		Empty logoutrequest = Empty.newBuilder().build();
//
//		APIResponse response1 = userStub.logout(logoutrequest);// calling the api's with that stubs
//
//		System.out.println(response1.getResponsemessage());
//
//	}

	ManagedChannel channel;

	//@GrpcClient("e2e-test")
	userBlockingStub userStub;

	public GrpcClient() {
		channel = ManagedChannelBuilder.forAddress("localhost", 9091).usePlaintext().build();
		userStub = userGrpc.newBlockingStub(channel);//block asynchronous call
	}

	public APIResponse Login(String Username, String Password) throws LoginNotFoundException {
		try {
			// login API
			LoginRequest loginrequest = LoginRequest.newBuilder().setUsername(Username).setPassword(Password).build();
			APIResponse response = userStub.login(loginrequest);
			System.out.println(response.getResponsemessage());
			System.out.println(response);
			return response;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			throw new LoginNotFoundException("User not found");
		}
	}

	public APIResponse Logout() throws LogoutNotFoundException {
		try {
			// logout API
			Empty logoutrequest = Empty.newBuilder().build();
			APIResponse response = userStub.logout(logoutrequest);
			System.out.println(response.getResponsemessage());
			System.out.println(response);
			return response;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			throw new LogoutNotFoundException("User not found");
		}
	}
}
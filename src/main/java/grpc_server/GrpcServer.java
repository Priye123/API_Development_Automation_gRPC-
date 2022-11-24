package grpc_server;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import user.UserService;

//starting grpc server in java at particular port.
public class GrpcServer {

	public Server server;

	public static void main(String args[]) throws IOException, InterruptedException {

//		System.out.println("starting GRPC Server");
//		//whenever we create object, we use build()
		Server server = ServerBuilder.forPort(9091).addService(new UserService()).build();
//
		server.start();
		System.out.println("server started at " + server.getPort());
		server.awaitTermination();
		// final GrpcServer server = new GrpcServer();
//		server.start1();
//		server.blockUntilShutdown();

	}

	public void start1(Server server) throws IOException {
		this.server = server;
		System.out.println("starting GRPC Server");
		// whenever we create object, we use build()
		server = ServerBuilder.forPort(9091).addService(new UserService()).build();
		server.start();
		System.out.println("server started at " + server.getPort());
	}

	public void stop() throws InterruptedException {
		if (server != null) {
			server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
		}
	}

	/**
	 * Await termination on the main thread since the grpc library uses daemon
	 * threads.
	 */
//	private void blockUntilShutdown() throws InterruptedException {
//		if (server != null) {
//			server.awaitTermination();
//		}
//	}
}

//how to test grpc services using GUI client called BloomRPC.  This tool is postman alternative for grpc services.
//Steps :
//1. Install BloomRPC
//2. connect to GRPC server by providing host address and port
//3. import proto files
//4. Run a service to test and get the response

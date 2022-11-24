package testcases;

import java.io.IOException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import grpc_server.GrpcServer;
import io.grpc.Server;

public class BaseTest {

	@BeforeSuite(alwaysRun = true)
	public void GRPCSetUp() throws IOException, InterruptedException {
		GrpcServer grpcServer = new GrpcServer();
		Server server = null;
		grpcServer.start1(server);

	}

	@AfterSuite(alwaysRun = true)
	public void GRPCTearDown() throws InterruptedException {
		GrpcServer grpcServer = new GrpcServer();
		grpcServer.stop();
	}
}

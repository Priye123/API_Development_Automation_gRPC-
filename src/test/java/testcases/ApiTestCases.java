package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.priye.grpc.User.APIResponse;

import exception.LoginNotFoundException;
import exception.LogoutNotFoundException;
import gRPC_Cilent.GrpcClient;

public class ApiTestCases extends BaseTest {
	//@Autowired
	private GrpcClient grpcClient;

	public ApiTestCases() {
		grpcClient = new GrpcClient();
	}

	@Test
	public void getLoginTests() throws LoginNotFoundException {
		APIResponse apiResponse = grpcClient.Login("Ram", "Ram");

		Assert.assertEquals(apiResponse.getResponsemessage(), "SUCCESS");
	}

	@Test
	public void getLogoutTests() throws LogoutNotFoundException {
		APIResponse apiResponse = grpcClient.Logout();

		Assert.assertEquals(apiResponse.getResponseCode(), 0);
	}
}

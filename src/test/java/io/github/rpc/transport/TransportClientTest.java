package io.github.rpc.transport;

import io.github.rpc.RpcRequest;
import io.github.rpc.RpcResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TransportClientTest {

	public static void main(String[] args) {

		TransportClient client = TransportClient.create();

		RpcRequest rpcRequest = new RpcRequest();
		rpcRequest.setHost("127.0.0.1");
		rpcRequest.setPort(4399);
	//	rpcRequest.setInterfaceName(null);
		rpcRequest.setMethodName("echo");
		rpcRequest.setParams(new Object[]{"pleuvoir"});
		rpcRequest.setImpl("io.github.rpc.provider.EchoServiceImpl");
	//	rpcRequest.setParamTypes(null);
		RpcResponse response = client.invokeSync(rpcRequest);
		
		log.info("response={}", response);
		
		
		client.shutdown();
	}
}

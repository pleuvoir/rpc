package io.github.rpc.invoker;

import io.github.rpc.RpcRequest;
import io.github.rpc.RpcResponse;
import io.github.rpc.api.ConsumerConfig;
import io.github.rpc.provider.EchoService;
import io.github.rpc.proxy.ConsumerInvoker;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConsumerInvokerTest {

	public static void main(String[] args) {

		ConsumerConfig consumerConfig = new ConsumerConfig();
		consumerConfig.setServiceInterface(EchoService.class);
		ConsumerInvoker consumerInvoker = new ConsumerInvoker(consumerConfig);

		RpcRequest rpcRequest = new RpcRequest();
		rpcRequest.setHost("127.0.0.1");
		rpcRequest.setPort(4399);
		// rpcRequest.setInterfaceName(null);
		rpcRequest.setMethodName("echo");
		rpcRequest.setParams(new Object[] { "pleuvoir" });
		rpcRequest.setImpl("io.github.rpc.provider.EchoServiceImpl");
		// rpcRequest.setParamTypes(null);

		RpcResponse response = consumerInvoker.invoke(rpcRequest);

		log.info("response={}", response);
	}

}

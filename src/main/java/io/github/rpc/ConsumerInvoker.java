package io.github.rpc;

import io.github.rpc.transport.TransportClient;

public class ConsumerInvoker implements Invoker {

	private TransportClient transportClient;

	@Override
	public RpcResponse invoke(RpcRequest request) {
		preHandlerRequest(request);
		RpcResponse response = transportClient.invokeSync(request);
		postHandlerResponse(response);
		return response;
	}

	protected void preHandlerRequest(RpcRequest request) {
	}

	protected void postHandlerResponse(RpcResponse response) {

	}

}

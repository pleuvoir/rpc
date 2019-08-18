package io.github.rpc.proxy;

import io.github.rpc.RpcRequest;
import io.github.rpc.RpcResponse;
import io.github.rpc.api.ServiceConfig;
import io.github.rpc.transport.TransportClient;

public class ConsumerInvoker implements Invoker {
	
	//远程对象
	private TransportClient transportClient = TransportClient.create();

	private final ServiceConfig serviceConfig;

	public ConsumerInvoker(ServiceConfig serviceConfig) {
		super();
		this.serviceConfig = serviceConfig;
	}


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

	@Override
	public ServiceConfig getServiceConfig() {
		return serviceConfig;
	}

}

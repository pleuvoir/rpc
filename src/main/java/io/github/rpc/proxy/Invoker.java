package io.github.rpc.proxy;

import io.github.rpc.RpcRequest;
import io.github.rpc.RpcResponse;
import io.github.rpc.api.ServiceConfig;

public interface Invoker {
	
	RpcResponse invoke(RpcRequest request);
	
	ServiceConfig getServiceConfig();
}

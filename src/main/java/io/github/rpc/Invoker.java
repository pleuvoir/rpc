package io.github.rpc;

public interface Invoker {
	
	RpcResponse invoke(RpcRequest request);
}

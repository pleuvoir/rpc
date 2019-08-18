package io.github.rpc.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import cn.hutool.core.lang.Singleton;
import io.github.rpc.RpcException;
import io.github.rpc.RpcRequest;
import io.github.rpc.RpcResponse;
import io.github.rpc.URL;
import io.github.rpc.api.ServiceConfig;
import io.github.rpc.registry.FileServiceRegistry;
import io.github.rpc.registry.RegistryServiceWrapper;

public class ConsumerInterceptor implements InvocationHandler {

	private final Invoker invoker;

	public ConsumerInterceptor(Invoker invoker) {
		super();
		this.invoker = invoker;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		ServiceConfig serviceConfig = invoker.getServiceConfig();

		FileServiceRegistry serviceRegistry = Singleton.get(FileServiceRegistry.class);

		RegistryServiceWrapper wrapper = serviceRegistry.getProviders(serviceConfig.getServiceName());

		if (wrapper == null) {
			throw new RpcException("没有可用的服务节点");
		}

		String serviceImpl = wrapper.getServiceImpl();

		//随便搞一个吧
		URL url = wrapper.getProviders().get(0).getUrl();

		RpcRequest rpcRequest = new RpcRequest();
		rpcRequest.setHost(url.getHost());
		rpcRequest.setPort(url.getPort());
		rpcRequest.setMethodName(method.getName());
		rpcRequest.setParams(args);
		rpcRequest.setImpl(serviceImpl);

		//远程调用
		RpcResponse rsp = invoker.invoke(rpcRequest);

		return rsp.getData();
	}

}

package io.github.rpc.api;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import io.github.rpc.proxy.ConsumerInterceptor;
import io.github.rpc.proxy.ConsumerInvoker;
import lombok.Getter;

public class Consumer {

	@Getter
	private final ConsumerConfig consumerConfig;

	private Object proxy;

	public Consumer(ConsumerConfig consumerConfig) {
		super();
		this.consumerConfig = consumerConfig;
	}

	/**
	 * 动态代理从注册中心引用一个消费者
	 * @param <T>
	 */
	@SuppressWarnings("unchecked")
	public <T> T ref() {
		if (proxy != null) {
			return (T) proxy;
		}
		proxy = getProxy(consumerConfig.getServiceInterface(),
				new ConsumerInterceptor(new ConsumerInvoker(consumerConfig)));
		return (T) proxy;
	}

	@SuppressWarnings({ "unchecked" })
	public <T> T getProxy(Class<T> serviceInterface, InvocationHandler h) {
		return (T) Proxy.newProxyInstance(getClass().getClassLoader(), new Class[] { serviceInterface }, h);
	}

}

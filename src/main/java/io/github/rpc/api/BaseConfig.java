package io.github.rpc.api;

import cn.hutool.core.lang.Singleton;
import io.github.rpc.URL;
import io.github.rpc.registry.FileServiceRegistry;
import io.github.rpc.registry.ServiceRegistry;

public class BaseConfig implements ServiceConfig {

	protected ServiceRegistry serviceRegistry = Singleton.get(FileServiceRegistry.class);

	protected Class<?> serviceInterface;

	protected URL serviceURL;

	public void register(String serviceName, String serviceImpl, URL url) {
		serviceRegistry.register(serviceName, serviceImpl, url);
	};

	@Override
	public URL getServiceURL() {
		return serviceURL;
	}

	@Override
	public void setServiceURL(URL serviceURL) {
		this.serviceURL = serviceURL;
	}

	@Override
	public String getServiceName() {
		return serviceInterface.getName();
	}

	@Override
	public void setServiceInterface(Class<?> service) {
		this.serviceInterface = service;
	}

	@Override
	public Class<?> getServiceInterface() {
		return serviceInterface;
	}

}
package io.github.rpc.registry;

import java.util.Set;

import io.github.rpc.URL;

public interface ServiceRegistry {

	void register(String serviceName, String serviceImpl, URL url);

	void unRegister(String serviceName);

	Set<URL> getURL(String serviceName);
	
	RegistryServiceWrapper getProviders(String serviceName);
}

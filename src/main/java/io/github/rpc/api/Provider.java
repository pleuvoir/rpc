package io.github.rpc.api;

import io.github.rpc.URL;
import io.github.rpc.transport.TransportServer;

public class Provider {

	private final ProviderConfig providerConfig;

	public Provider(ProviderConfig providerConfig) {
		super();
		this.providerConfig = providerConfig;
	}

	public void export() {
		URL url = providerConfig.getServiceURL();
		String serviceName = providerConfig.getServiceName();
		Class<?> serviceImplClazz = providerConfig.getServiceImplClazz();
		providerConfig.register(serviceName, serviceImplClazz.getName(), url);
		TransportServer transportServer = TransportServer.create(4399);
		transportServer.start();
	}

}

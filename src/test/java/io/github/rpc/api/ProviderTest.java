package io.github.rpc.api;

import io.github.rpc.URL;
import io.github.rpc.provider.EchoService;
import io.github.rpc.provider.EchoServiceImpl;

public class ProviderTest {

	public static void main(String[] args) {
		ProviderConfig providerConfig = new ProviderConfig();
		providerConfig.setServiceInterface(EchoService.class);
		//providerConfig.setServiceImpl("io.github.rpc.provider.EchoServiceImpl");
		providerConfig.setServiceImplClazz(EchoServiceImpl.class);
		providerConfig.setServiceURL(new URL("127.0.0.1", 4399));
		new Provider(providerConfig).export();
		
		//System.exit(-1);
	}
}

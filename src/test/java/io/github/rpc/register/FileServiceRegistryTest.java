package io.github.rpc.register;

import java.util.Set;

import io.github.rpc.URL;
import io.github.rpc.registry.FileServiceRegistry;
import io.github.rpc.registry.ServiceRegistry;

public class FileServiceRegistryTest {

	public static void main(String[] args) {

		ServiceRegistry serviceRegistry = new FileServiceRegistry();

		serviceRegistry.register("test_1", "io.impl.test_1", new URL("192.168.1.1", 8080));

		serviceRegistry.register("test_2", "io.impl.test_2", new URL("192.168.1.2", 8081));
		serviceRegistry.register("test_2", "io.impl.test_2", new URL("192.168.1.2", 8082));
		serviceRegistry.register("test_2", "io.impl.test_2", new URL("192.168.1.2", 8082));

		Set<URL> url = serviceRegistry.getURL("test_1");

		System.out.println(url);

		serviceRegistry.unRegister("test_1");

		System.out.println(url);
	}

}

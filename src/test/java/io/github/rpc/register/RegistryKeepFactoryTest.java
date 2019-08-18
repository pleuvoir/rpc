package io.github.rpc.register;

import java.util.Arrays;
import java.util.List;

import io.github.rpc.ServiceConsumer;
import io.github.rpc.ServiceProvider;
import io.github.rpc.URL;
import io.github.rpc.registry.RegistryKeepFactory;
import io.github.rpc.registry.RegistryServiceWrapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RegistryKeepFactoryTest {

	public static void main(String[] args) {

		RegistryKeepFactory factory = new RegistryKeepFactory();

		
		factory.Initialization();
		
		List<RegistryServiceWrapper> currentRegistryServiceWrapper = factory.currentRegistryServiceWrapper();
		currentRegistryServiceWrapper.forEach(k -> {
			log.info(k.getServiceName());
		});
		
		
		factory.clearAll();
		
		RegistryServiceWrapper wrapper = new RegistryServiceWrapper();
		wrapper.setServiceName("EchoService");

		ServiceProvider serviceProvider = new ServiceProvider();
		serviceProvider.setUrl(new URL("127.0.0.1", 4399));
		wrapper.setProviders(Arrays.asList(serviceProvider));

		ServiceConsumer serviceConsumer = new ServiceConsumer();
		serviceConsumer.setUrl(new URL("127.0.0.1", 4000));
		wrapper.setConsumers(Arrays.asList(serviceConsumer));
		
		factory.save(wrapper);

		RegistryServiceWrapper wrapper2 = new RegistryServiceWrapper();
		wrapper2.setServiceName("EchoService2");

		ServiceProvider serviceProvider2 = new ServiceProvider();
		serviceProvider2.setUrl(new URL("127.0.0.1", 4399));
		wrapper2.setProviders(Arrays.asList(serviceProvider2));

		ServiceConsumer serviceConsumer2 = new ServiceConsumer();
		serviceConsumer2.setUrl(new URL("127.0.0.1", 4001));
		
		ServiceConsumer serviceConsumer2_2 = new ServiceConsumer();
		serviceConsumer2_2.setUrl(new URL("127.0.0.1", 4002));
		
		wrapper2.setConsumers(Arrays.asList(serviceConsumer2, serviceConsumer2_2));

		factory.save(wrapper2);
		
		
		
		RegistryServiceWrapper wrapper2_repeat = new RegistryServiceWrapper();
		wrapper2_repeat.setServiceName("EchoService2");

		ServiceProvider serviceProvider2_repeat = new ServiceProvider();
		serviceProvider2_repeat.setUrl(new URL("127.0.0.1", 4399));
		wrapper2_repeat.setProviders(Arrays.asList(serviceProvider2));

		ServiceConsumer serviceConsumer2_repeat = new ServiceConsumer();
		serviceConsumer2_repeat.setUrl(new URL("127.0.0.1", 4001));
		
		ServiceConsumer serviceConsumer2_2_repeat = new ServiceConsumer();
		serviceConsumer2_2_repeat.setUrl(new URL("127.0.0.1", 4002));
		
		wrapper2_repeat.setConsumers(Arrays.asList(serviceConsumer2_repeat, serviceConsumer2_2_repeat));
		
		
		factory.save(wrapper2_repeat);
		
		factory.remove("EchoService");
		
		
		currentRegistryServiceWrapper.forEach(k -> {
			log.info(k.getServiceName());
		});
		
		
		System.exit(-1);
	}

}

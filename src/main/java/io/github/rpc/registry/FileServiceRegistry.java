package io.github.rpc.registry;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import io.github.rpc.ServiceProvider;
import io.github.rpc.URL;

public class FileServiceRegistry implements ServiceRegistry {

	private RegistryKeepFactory factory;

	public FileServiceRegistry() {
		factory = new RegistryKeepFactory();
		factory.Initialization();
	}

	@Override
	public void register(String serviceName, String serviceImpl, URL url) {
		RegistryServiceWrapper wrapper = new RegistryServiceWrapper();
		wrapper.setServiceName(serviceName);
		wrapper.setServiceImpl(serviceImpl);
		// 获取当前服务商
		Set<URL> prevProviders = getURL(serviceName);
		// 添加此次加入的
		prevProviders.add(url);
		List<ServiceProvider> providers = Lists.newArrayList();
		for (URL prev : prevProviders) {
			ServiceProvider serviceProvider = new ServiceProvider();
			serviceProvider.setUrl(prev);
			providers.add(serviceProvider);
		}
		wrapper.setProviders(providers);
		factory.save(wrapper);
	}

	@Override
	public void unRegister(String serviceName) {
		factory.remove(serviceName);
	}

	@Override
	public Set<URL> getURL(String serviceName) {

		Optional<RegistryServiceWrapper> findFirst = factory.currentRegistryServiceWrapper().stream()
				.filter(k -> k.getServiceName().equals(serviceName)).findFirst();

		if (findFirst.isPresent()) {
			return findFirst.get().getProviders().stream().map(mapper -> mapper.getUrl()).collect(Collectors.toSet());
		} else {
			return Sets.newHashSet();
		}
	}

	@Override
	public RegistryServiceWrapper getProviders(String serviceName) {
		Optional<RegistryServiceWrapper> findFirst = factory.currentRegistryServiceWrapper().stream()
				.filter(k -> k.getServiceName().equals(serviceName)).findFirst();

		if (findFirst.isPresent()) {
			return findFirst.get();
		} else {
			return null;
		}
	}
}

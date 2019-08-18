package io.github.rpc.api;

public class ProviderConfig extends BaseConfig {

	private Class<?> serviceImplClazz;
	private String serviceImpl;

	public String getServiceImpl() {
		return serviceImpl;
	}

	public void setServiceImpl(String serviceImpl) {
		this.serviceImpl = serviceImpl;
	}

	public Class<?> getServiceImplClazz() {
		return serviceImplClazz;
	}

	public void setServiceImplClazz(Class<?> serviceImplClazz) {
		this.serviceImplClazz = serviceImplClazz;
	}

}

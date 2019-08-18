package io.github.rpc.api;

import io.github.rpc.URL;

public interface ServiceConfig {

	String getServiceName();

	void setServiceInterface(Class<?> service);

	Class<?> getServiceInterface();

	URL getServiceURL();
	
	void setServiceURL(URL url);
}

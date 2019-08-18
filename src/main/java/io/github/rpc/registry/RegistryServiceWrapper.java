package io.github.rpc.registry;

import java.util.List;
import java.util.Objects;

import io.github.rpc.ServiceConsumer;
import io.github.rpc.ServiceProvider;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistryServiceWrapper {

	private String serviceName;
	
	private String serviceImpl;

	private List<ServiceProvider> providers;

	private List<ServiceConsumer> consumers;

	/**
	 * 服务名相同即认为是相同对象
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof RegistryServiceWrapper))
			return false;
		RegistryServiceWrapper that = (RegistryServiceWrapper) o;
		return Objects.equals(serviceName, that.serviceName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(serviceName);
	}
}

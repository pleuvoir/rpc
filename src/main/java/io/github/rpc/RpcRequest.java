package io.github.rpc;

import java.util.Arrays;

import lombok.Data;

@Data
public class RpcRequest {

	private String interfaceName;
	private String methodName;
	private Object[] params;
	private Class<?>[] paramTypes;
	private String impl;
	private String host;
	private int port;

	@Override
	public String toString() {
		return String.format(
				"RpcRequest [interfaceName=%s, methodName=%s, params=%s, paramTypes=%s, impl=%s, host=%s, port=%s]",
				interfaceName, methodName, Arrays.toString(params), Arrays.toString(paramTypes), impl, host, port);
	}

}

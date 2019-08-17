package io.github.rpc;

public class RpcException extends RuntimeException {

	private static final long serialVersionUID = -796418770730829157L;

	public RpcException() {
		super();
	}

	public RpcException(String message, Throwable cause) {
		super(message, cause);
	}

	public RpcException(String message) {
		super(message);
	}

	public RpcException(Throwable cause) {
		super(cause);
	}

}

package io.github.rpc.transport;

import io.github.pikaq.server.RemotingServer;
import io.github.pikaq.server.ServerConfig;
import io.github.pikaq.server.SimpleServer;
import lombok.Getter;

public class TransportServer {

	private RemotingServer remotingServer;

	@Getter
	private final int port;

	public static TransportServer create(int port) {
		return new TransportServer(port);
	}

	private TransportServer(int port) {
		this.port = port;
	}

	public void start() {
		ServerConfig serverConfig = ServerConfig.create(port);
		remotingServer = new SimpleServer(serverConfig);
		remotingServer.registerHandler(RpcRequestCode.REQUEST, new RpcRequestProcessor());
		remotingServer.start();
	}

}

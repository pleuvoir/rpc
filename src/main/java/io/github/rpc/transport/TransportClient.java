package io.github.rpc.transport;

import io.github.pikaq.client.ClientConfig;
import io.github.pikaq.client.RemotingClient;
import io.github.pikaq.client.SimpleClient;
import io.github.pikaq.common.exception.RemotingSendRequestException;
import io.github.pikaq.common.exception.RemotingTimeoutException;
import io.github.rpc.RpcException;
import io.github.rpc.RpcRequest;
import io.github.rpc.RpcResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TransportClient {

	private RemotingClient remotingClient;

	private TransportClient() {
		initRemotingClient();
	}

	public static TransportClient create() {
		return new TransportClient();
	}

	public RpcResponse invokeSync(RpcRequest rpcRequest) {
		RpcRequestCommand command = new RpcRequestCommand();
		command.setRpcRequest(rpcRequest);
		try {
			RpcResponseCommand rsp = (RpcResponseCommand) this.remotingClient
					.invokeSync(rpcRequest.getHost() + ":" + rpcRequest.getPort(), command, 2000);
			return rsp.getRpcResponse();
		} catch (RemotingTimeoutException | RemotingSendRequestException e) {
			log.error("invokeSync ,", e);
			throw new RpcException(e.getMessage());
		}
	}
	
	public void shutdown(){
		remotingClient.shutdown();
	}

	private void initRemotingClient() {
		if (remotingClient == null) {
			ClientConfig clientConfig = ClientConfig.create().heartbeatIntervalSeconds(0).build();
			remotingClient = new SimpleClient(clientConfig);
		}
	}
}

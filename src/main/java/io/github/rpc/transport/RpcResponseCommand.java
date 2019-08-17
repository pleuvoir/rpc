package io.github.rpc.transport;

import io.github.pikaq.protocol.command.RemoteBaseCommand;
import io.github.pikaq.protocol.command.RemotingCommandType;
import io.github.rpc.RpcResponse;

public class RpcResponseCommand extends RemoteBaseCommand {

	private RpcResponse rpcResponse;

	public RpcResponse getRpcResponse() {
		return rpcResponse;
	}

	public void setRpcResponse(RpcResponse rpcResponse) {
		this.rpcResponse = rpcResponse;
	}

	@Override
	public boolean responsible() {
		return false;
	}

	@Override
	public int requestCode() {
		return RpcRequestCode.RESPONSE;
	}

	@Override
	public RemotingCommandType remotingCommandType() {
		return RemotingCommandType.RESPONSE_COMMAND;
	}

}

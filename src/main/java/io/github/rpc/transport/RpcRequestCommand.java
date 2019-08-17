package io.github.rpc.transport;

import io.github.pikaq.protocol.command.RemoteBaseCommand;
import io.github.pikaq.protocol.command.RemotingCommandType;
import io.github.rpc.RpcRequest;

public class RpcRequestCommand extends RemoteBaseCommand {

	private RpcRequest rpcRequest;

	public RpcRequest getRpcRequest() {
		return rpcRequest;
	}

	public void setRpcRequest(RpcRequest rpcRequest) {
		this.rpcRequest = rpcRequest;
	}

	@Override
	public boolean responsible() {
		return true;
	}

	@Override
	public int requestCode() {
		return RpcRequestCode.REQUEST;
	}

	@Override
	public RemotingCommandType remotingCommandType() {
		return RemotingCommandType.REQUEST_COMMAND;
	}

}

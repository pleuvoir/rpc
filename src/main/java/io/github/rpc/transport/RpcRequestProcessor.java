package io.github.rpc.transport;

import cn.hutool.core.util.ClassUtil;
import io.github.pikaq.protocol.RemotingRequestProcessor;
import io.github.rpc.RpcRequest;
import io.github.rpc.RpcResponse;
import io.netty.channel.ChannelHandlerContext;

public class RpcRequestProcessor implements RemotingRequestProcessor<RpcRequestCommand, RpcResponseCommand> {

	@Override
	public RpcResponseCommand handler(ChannelHandlerContext ctx, RpcRequestCommand request) {

		RpcResponseCommand rpcResponseCommand = new RpcResponseCommand();

		RpcRequest rpcRequest = request.getRpcRequest();
		String interfaceName = rpcRequest.getInterfaceName();
		String methodName = rpcRequest.getMethodName();
		Class<?>[] paramTypes = rpcRequest.getParamTypes();
		Object[] params = rpcRequest.getParams();
		String impl = rpcRequest.getImpl();
		Object result = ClassUtil.invoke(impl + "#" + methodName, false, params);

		RpcResponse rpcResponse = new RpcResponse();
		rpcResponse.setData(result);

		rpcResponseCommand.setRpcResponse(rpcResponse);

		return rpcResponseCommand;
	}

}

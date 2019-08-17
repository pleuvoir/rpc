package io.github.rpc.provider;

public class EchoServiceImpl implements EchoService {

	@Override
	public String echo(String name) {
		return "reply:" + name;
	}

}

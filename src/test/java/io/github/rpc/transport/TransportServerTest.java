package io.github.rpc.transport;

public class TransportServerTest {

	public static void main(String[] args) {
		TransportServer transportServer = TransportServer.create(4399);
		transportServer.start();
	}

}

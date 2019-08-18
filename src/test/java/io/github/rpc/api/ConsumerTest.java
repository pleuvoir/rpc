package io.github.rpc.api;

import java.util.concurrent.TimeUnit;

import io.github.rpc.provider.EchoService;

public class ConsumerTest {

	public static void main(String[] args) throws InterruptedException {

		ConsumerConfig consumerConfig = new ConsumerConfig();
		consumerConfig.setServiceInterface(EchoService.class);
		

		Consumer consumer = new Consumer(consumerConfig);

		EchoService ref = consumer.ref();
		
		
		for (;;) {
			String result = ref.echo("你好");
			System.out.println(result);
			TimeUnit.SECONDS.sleep(1);
		}
	}

}

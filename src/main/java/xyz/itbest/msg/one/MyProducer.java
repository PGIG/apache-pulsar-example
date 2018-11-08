package xyz.itbest.msg.one;

import org.apache.pulsar.client.api.*;
import org.apache.pulsar.client.impl.conf.ProducerConfigurationData;

import java.util.concurrent.TimeUnit;

/**
 * @author pgig
 * @date 2018/11/7 17:34
 */
public class MyProducer {

    public static void main(String[] args) throws PulsarClientException {
        PulsarClient client = PulsarClient.builder()
                .serviceUrl("pulsar://localhost:6650")
                .build();
        AlwaysTenRouter alwaysTenRouter = new AlwaysTenRouter();

        //SinglePartition RoundRobinPartition CustomPartition
        Producer<byte[]> producer = client.newProducer()
                .topic("my-topic")
                .messageRoutingMode(MessageRoutingMode.SinglePartition)
                .messageRouter(alwaysTenRouter)
                .create();

        producer.send("Partitioned topic message".getBytes());

        //producer.closeAsync().thenRun(() -> System.out.println("Producer closed"));
        producer.close();
        client.close();
    }

}

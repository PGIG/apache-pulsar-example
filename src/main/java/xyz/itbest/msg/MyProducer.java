package xyz.itbest.msg;

import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.Schema;

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

        Producer<byte[]> producer = client.newProducer()
                .topic("my-topic")
                .batchingMaxPublishDelay(10, TimeUnit.MILLISECONDS)
                .sendTimeout(10,TimeUnit.SECONDS)
                .blockIfQueueFull(true)
                .create();

        producer.send("My message".getBytes());


        Producer<String> stringProducer = client.newProducer(Schema.STRING)
                .topic("my-topic")
                .create();

        stringProducer.send("My message");

        //producer.closeAsync().thenRun(() -> System.out.println("Producer closed"));
        producer.close();
        client.close();
    }

}

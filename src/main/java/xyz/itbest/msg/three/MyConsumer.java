package xyz.itbest.msg.three;

import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;

/**
 * @author pgig
 * @date 2018/11/7 17:34
 */
public class MyConsumer {
    public static void main(String[] args) throws PulsarClientException {
        PulsarClient client = PulsarClient.builder()
                .serviceUrl("pulsar://localhost:6650")
                .build();

        Consumer consumer = client.newConsumer()
                .topic("my-topic")
                .subscriptionName("my-subscription")
                .subscribe();

        do {
            // Wait for a message
            Message msg = consumer.receive();

            System.out.printf("Message received: %s \n", new String(msg.getData()));
            System.out.printf("Message received: %s \n", msg.getProperty("my-key"));
            System.out.printf("Message received: %s \n", msg.getProperty("my-other-key"));


            // Acknowledge the message so that it can be deleted by the message broker
            consumer.acknowledge(msg);
        } while (true);
    }
}

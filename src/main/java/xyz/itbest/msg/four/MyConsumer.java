package xyz.itbest.msg.four;

import org.apache.pulsar.client.api.*;

import java.util.regex.Pattern;

/**
 * @author pgig
 * @date 2018/11/7 17:34
 */
public class MyConsumer {
    public static void main(String[] args) throws PulsarClientException {
        sync();
    }

    public static void sync() throws PulsarClientException{
        PulsarClient client = PulsarClient.builder()
                .serviceUrl("pulsar://localhost:6650")
                .build();

        ConsumerBuilder consumerBuilder = client.newConsumer()
                .subscriptionName("my-subscription");

        //persistent://public/default/foo.*
        //Pattern topicsInNamespace = Pattern.compile("persistent://public/default/.*");

        Consumer consumer = consumerBuilder
                //.topicsPattern(topicsInNamespace)
                .topic("test-1","test-a","test-!")
                .subscribe();

        do {
            // Wait for a message
            Message msg = consumer.receive();

            System.out.printf("Message received: %s \n", new String(msg.getData()));

            // Acknowledge the message so that it can be deleted by the message broker
            consumer.acknowledge(msg);
        } while (true);
    }

}

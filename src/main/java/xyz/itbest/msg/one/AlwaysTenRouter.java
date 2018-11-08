package xyz.itbest.msg.one;

import org.apache.pulsar.client.api.MessageRouter;
import org.apache.pulsar.shade.com.google.protobuf.Message;

import java.io.Serializable;

/**
 * @author pgig
 * @date 2018/11/8 14:04
 */
public class AlwaysTenRouter implements MessageRouter {

    public int choosePartition(Message msg) {
        return 10;
    }

}

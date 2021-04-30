package handsome.matt.kafka.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * @author Matthew
 * @date 2020/12/6 15:10
 */
@Component
public class MtKafkaProducer {
    @Autowired
    private KafkaTemplate<Object, Object> kafkaTemplate;

    public void send()
    {

        ListenableFuture<SendResult<Object, Object>> future = kafkaTemplate.send("test", 1, "哈哈哈哈呵呵");

        // You can register a callback with the listener to receive the result
        // of the send asynchronously. The following example shows how to do so:
        future.addCallback(new ListenableFutureCallback<SendResult<Object, Object>>() {
            @Override
            public void onFailure(Throwable ex) {

            }

            @Override
            public void onSuccess(SendResult<Object, Object> result) {

            }
        });
    }


}

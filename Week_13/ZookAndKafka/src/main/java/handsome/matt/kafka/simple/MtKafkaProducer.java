package handsome.matt.kafka.simple;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @author Matthew
 * @date 2020/12/5 20:43
 */
public class MtKafkaProducer extends Thread{
    //producer Api
    KafkaProducer<Integer,String> producer;
    String topic;

    public MtKafkaProducer(String topic) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.31.133:9092");
        properties.put(ProducerConfig.CLIENT_ID_CONFIG,"ma_producer");
        properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, "handsome.matt.kafka.simple.MyPartition");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        // 连接的字符串
        // 通过工厂
        // new
        producer = new KafkaProducer<>(properties);
        this.topic = topic;
    }

    @Override
    public void run() {
        int num = 0;
        while (num < 20)
        {
            try {
                String msg = "Matthew kafka practive msg:" + num;
                //get 会拿到发送的结果
                //同步 get() -> Future()
                //回调通知
                producer.send(new ProducerRecord<>(topic, 1, msg), (metadata, exception) -> {
                    System.out.println(metadata.offset() + "->" + metadata.partition() + "->" + metadata.topic());
                });
                TimeUnit.SECONDS.sleep(2);
                ++num;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new MtKafkaProducer("test_partition").start();
    }
}

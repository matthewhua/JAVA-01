package handsome.matt.kafka.simple;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

/**
 * @author Matthew
 * @date 2020/12/5 17:16
 */
public class MyPartition implements Partitioner {


    /**
     * @return 在这里进行了分片哦
     */
    @Override
    public int partition(String topic, Object key, byte[] bytes, Object o1, byte[] bytes1, Cluster cluster) {
        System.out.println("Do you want enter");
        List<PartitionInfo> info = cluster.availablePartitionsForTopic(topic);
        int len = info.size();
        if (key == null){
            Random random = new Random();
            return random.nextInt(len);
        }
        return Math.abs(key.hashCode())%len;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}

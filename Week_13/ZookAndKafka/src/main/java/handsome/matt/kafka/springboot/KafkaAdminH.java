package handsome.matt.kafka.springboot;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Matthew
 * @date 2020/12/7 17:51
 */

@Configuration
public class KafkaAdminH {

  /*  @Bean
    public KafkaAdmin admin() {
        {
            Map<String, Object> configs = new HashMap<>();
            configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,
                    StringUtils.arrayToCommaDelimitedString(StringUtils.sortStringArray(int[])));
            return new KafkaAdmin(configs);
        }
    }*/





}

package handsome.matt.kafka.springboot;

import handsome.matt.kafka.springboot.bean.Bar;
import handsome.matt.kafka.springboot.bean.Foo;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.kafka.support.converter.DefaultJackson2JavaTypeMapper;
import org.springframework.kafka.support.converter.Jackson2JavaTypeMapper;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Matthew
 * @date 2020/12/7 19:54
 */
@Configuration
public class KafkaConfig {

    @Bean
    public ConcurrentKafkaListenerContainerFactory<?, ?> kafkaListenerContainerFactory(
        ConcurrentKafkaListenerContainerFactoryConfigurer configure,
        ConsumerFactory<Object, Object> kafkaConsumerFactory,
        KafkaTemplate<Object, Object> template) {
        ConcurrentKafkaListenerContainerFactory<Object, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        configure.configure(factory, kafkaConsumerFactory);
        factory.setErrorHandler(new SeekToCurrentErrorHandler(new DeadLetterPublishingRecoverer(template), 3));
        return factory;
    }


    // 当传输的是个实体类时，进行消息格式转换
    @Bean
    public RecordMessageConverter converter()
    {
        StringJsonMessageConverter converter = new StringJsonMessageConverter();
        DefaultJackson2JavaTypeMapper typeMapper = new DefaultJackson2JavaTypeMapper();
        typeMapper.setTypePrecedence(Jackson2JavaTypeMapper.TypePrecedence.TYPE_ID);
        typeMapper.addTrustedPackages("handsome.matt.kafka.springboot.bean");
        Map<String, Class<?>> mapping = new HashMap<>();
        mapping.put("foo", Foo.class);
        mapping.put("bar", Bar.class);
        typeMapper.setIdClassMapping(mapping);
        converter.setTypeMapper(typeMapper);
        return converter;
    }

   /* @Bean
    public void toSpring(boolean istre){
        if (istre){
            System.out.println("年轻人劝你耗子为之");
        }
    }*/
}

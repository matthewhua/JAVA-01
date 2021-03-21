package io.matt.provider;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Matthew
 * @date 2021-03-21 8:32
 */


@EnableDubbo
@SpringBootApplication
@MapperScan("com.gujie.provider.mapper")
public class ProviderApplication {
}

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Matthew
 * @date 2021-02-21 1:48
 */

@SpringBootApplication
@EnableSwagger2
public class JDBCApplication {
    public static void main(String[] args) {
        SpringApplication.run(JDBCApplication.class,args).start();
    }
}

package sweet.framework.service.framework.web.springboot;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAutoConfiguration
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "sweet.framework")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

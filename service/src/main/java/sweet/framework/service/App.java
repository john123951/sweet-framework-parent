package sweet.framework.service;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Date;

/**
 * Hello world!
 */
@Configuration
@ComponentScan("sweet.framework")
@EnableAspectJAutoProxy //启用AOP
public class App {
    public static void main(String[] args) {
//        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
//        ctx.scan("sweet.framework");
//        ctx.refresh();

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(App.class);

        IUserService userService = ctx.getBean(IUserService.class);
        Date time = userService.getTime();

        System.out.println(time);

        System.out.println("OK");
    }
}

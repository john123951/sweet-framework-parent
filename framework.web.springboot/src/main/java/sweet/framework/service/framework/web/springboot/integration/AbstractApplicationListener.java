package sweet.framework.service.framework.web.springboot.integration;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.core.Ordered;

/**
 * spring boot 初始化抽象类 ，其实现类适合通过META-INF/spring.factories的方式来引入到应用中。<br>
 * <p>
 * spring.factories文件举例：
 * <p>
 * org.springframework.context.ApplicationListener=com.cifpay.lc.gateway.integration.GatewayApplicationListener
 */
public abstract class AbstractApplicationListener implements org.springframework.context.ApplicationListener<ApplicationStartedEvent>, Ordered {

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        // 修改loggingSystem的启动类
        System.setProperty("org.springframework.boot.logging.LoggingSystem", AbstractLoggingSystem.class.getName());
    }

}

package sweet.framework.service.framework.web.springboot.integration;

import org.springframework.boot.logging.LogFile;
import org.springframework.boot.logging.LoggingInitializationContext;
import org.springframework.boot.logging.logback.LogbackLoggingSystem;
import org.springframework.core.io.ClassPathResource;

import java.io.File;

/**
 * 修改logback配置文件的读取位置
 */
public abstract class AbstractLoggingSystem extends LogbackLoggingSystem {

    public AbstractLoggingSystem(ClassLoader classLoader) {
        super(classLoader);
    }

    /**
     * 获取WAR应用之外的属于当前环境（例如SIT环境、UAT环境、生产环境）的logback配置文件的外部路径，例如C:/abc/hello.xml
     *
     * @return
     */
    protected abstract String getEnvSpecifiedExternalConfigPath();

    /**
     * 获取WAR应用之外的classpath范围的logback配置文件的包路径，例如abc/hello.xml
     *
     * @return
     */
    protected abstract String getDefaultClasspathConfigPath();

    @Override
    protected void loadConfiguration(LoggingInitializationContext initializationContext, String location, LogFile logFile) {
        super.loadConfiguration(initializationContext, location, logFile);
    }

    @Override
    protected String getSelfInitializationConfig() {
        String specifiedConfig = getEnvSpecifiedExternalConfigPath();
        String classpathConfig = getDefaultClasspathConfigPath();

        // 搜索文件系统中是否存在配置文件
        if (new File(specifiedConfig).exists()) {
            return specifiedConfig;
        }

        // 搜索classpath中是否存在配置文件
        ClassPathResource resource = new ClassPathResource(classpathConfig, getClassLoader());
        if (resource.exists()) {
            return "classpath:" + classpathConfig;
        }

        // 使用默认配置
        return super.getSelfInitializationConfig();
    }
}
package sweet.framework.service;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.*;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import sweet.framework.service.dto.UserDto;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

/**
 * Hello world!
 */
@Configuration
@ComponentScan("sweet.framework")
@EnableAspectJAutoProxy //启用AOP
public class App {

    @Bean
    public DataSource dataSource() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://192.168.1.200:3306/example?useUnicode=true&characterEncoding=UTF-8");
        dataSource.setUsername("dev");
        dataSource.setPassword("dev");

        // 初始化连接大小
        dataSource.setInitialSize(0);
        // 连接池最大使用连接数量
        dataSource.setMaxActive(20);
        // 连接池最大空闲
        dataSource.setMaxIdle(20);
        // 连接池最小空闲
        dataSource.setMinIdle(0);
        // 获取连接最大等待时间
        dataSource.setMaxWait(60000);

        dataSource.setValidationQuery("SELECT 1");
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);
        dataSource.setTestWhileIdle(true);

        // 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        dataSource.setTimeBetweenEvictionRunsMillis(60000);
        // 配置一个连接在池中最小生存的时间，单位是毫秒
        dataSource.setMinEvictableIdleTimeMillis(25200000);

        // 打开removeAbandoned功能
        dataSource.setRemoveAbandoned(true);
        // 1800秒，也就是30分钟
        dataSource.setRemoveAbandonedTimeout(1800);

        //关闭abanded连接时输出错误日志
        dataSource.setLogAbandoned(false);

        //监控数据库
        dataSource.setFilters("mergeStat");

        return dataSource;
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);

        try {
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sqlSessionFactoryBean;
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("sweet.framework.repository.dao");

        return mapperScannerConfigurer;
    }

    public static void main(String[] args) {
        // 手动配置
        // AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        // ctx.scan("sweet.framework");
        //  ctx.refresh();

        // 自动配置
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(App.class);

        IUserService userService = ctx.getBean(IUserService.class);

        System.out.println("调用接口实现：");
        Date time = userService.getTime();
        System.out.println(time);

        System.out.println("从数据库中读取数据：");
        UserDto userDto = userService.getUserById(1L);
        System.out.println(userDto);

        System.out.println("OK");
    }
}

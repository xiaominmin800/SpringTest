package config;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;

/**
 * created by chenminqing
 */
@Configuration
@EnableTransactionManagement
@ImportResource(value = "classpath:/mybatis.xml")
@MapperScan(basePackages = "dao.mapper")
@Slf4j
public class MybatisConfig implements TransactionManagementConfigurer {

    @Autowired(required = false)
    private DataSource dataSource;

    @Bean(name = "sqlSessionFactory")
    @ConditionalOnMissingBean
    public SqlSessionFactory sqlSessionFactoryBean() {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);

        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            bean.setConfigLocation(resolver.getResource("classpath:/mybatis.xml"));
            bean.setMapperLocations(resolver.getResources("classpath:/mapper/*.xml"));
            return bean.getObject();
        } catch (Exception e) {
            log.info("RuntimeException : {}" , e);
            throw new RuntimeException("INTERNAL_SERVER_ERROR", e);
        }
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate() {
        return new SqlSessionTemplate(sqlSessionFactoryBean());
    }

    @Bean
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }

}

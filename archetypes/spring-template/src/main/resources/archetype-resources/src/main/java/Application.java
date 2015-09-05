#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@PropertySource("classpath:application.properties")
@ComponentScan
@EnableAutoConfiguration
@Configuration
@EnableScheduling
@EnableAspectJAutoProxy
@EnableCaching
@EnableAsync
@EnableJpaRepositories(basePackages = { "${package}.repositories" })
@EnableTransactionManagement
public class Application {

    private static Logger logger = LoggerFactory.getLogger(Application.class);

    // @Autowired
    // private PersonBusiness userService;

    @Value("${symbol_dollar}{init-db:false}")
    private String initDatabase;

    @Resource
    private Environment env;

    @Bean
    public PlatformTransactionManager transactionManager() {
        EntityManagerFactory factory = entityManagerFactory().getObject();
        return new JpaTransactionManager(factory);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(Boolean.TRUE);
        vendorAdapter.setShowSql(Boolean.TRUE);

        factory.setDataSource(dataSource());
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("${package}.entities");

        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.hbm2ddl.auto",
                env.getProperty("hibernate.hbm2ddl.auto"));
        jpaProperties.put("hibernate.dialect",
                env.getProperty("hibernate.dialect"));

        factory.setJpaProperties(jpaProperties);

        factory.afterPropertiesSet();
        factory.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
        return factory;
    }

    @Bean
    public HibernateExceptionTranslator hibernateExceptionTranslator() {
        return new HibernateExceptionTranslator();
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(env.getProperty("jdbc.url"));
        dataSource.setUsername(env.getProperty("jdbc.username"));
        dataSource.setPassword(env.getProperty("jdbc.password"));
        return dataSource;
    }

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager();
    }

    /*
     * @Bean public JavaMailSenderImpl javaMailSenderImpl() { JavaMailSenderImpl
     * mailSenderImpl = new JavaMailSenderImpl();
     * mailSenderImpl.setHost(env.getProperty("smtp.host"));
     * mailSenderImpl.setPort(env.getProperty("smtp.port", Integer.class));
     * mailSenderImpl.setProtocol(env.getProperty("smtp.protocol"));
     * mailSenderImpl.setUsername(env.getProperty("smtp.username"));
     * mailSenderImpl.setPassword(env.getProperty("smtp.password"));
     * 
     * Properties javaMailProps = new Properties();
     * javaMailProps.put("mail.smtp.auth", true);
     * javaMailProps.put("mail.smtp.starttls.enable", true);
     * 
     * mailSenderImpl.setJavaMailProperties(javaMailProps);
     * 
     * return mailSenderImpl; }
     */

    @PostConstruct
    public void init() {
        try {
            logger.info("${symbol_pound}${symbol_pound}${symbol_pound}${symbol_pound}${symbol_pound}${symbol_pound}${symbol_pound}${symbol_pound}${symbol_pound}${symbol_pound}${symbol_pound}${symbol_pound}${symbol_pound}${symbol_pound} InitDatabase :" + initDatabase
                    + " ${symbol_pound}${symbol_pound}${symbol_pound}${symbol_pound}${symbol_pound}${symbol_pound}${symbol_pound}${symbol_pound}${symbol_pound}${symbol_pound}${symbol_pound}${symbol_pound}${symbol_pound}${symbol_pound}${symbol_pound}${symbol_pound}${symbol_pound}${symbol_pound}${symbol_pound}${symbol_pound}${symbol_pound}${symbol_pound}${symbol_pound}${symbol_pound}");
            if (Boolean.parseBoolean(initDatabase)) {
                initDatabase();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void initDatabase() {
        logger.info("Initializing Database with sample data");

        /*
         * Role role1 = new Role("ROLE_USER"); Role role2 = new
         * Role("ROLE_ADMIN");
         * 
         * User user1 = new User(1, "admin", "admin", "Administrator",
         * "admin@gmail.com"); User user2 = new User(2, "siva", "siva", "Siva",
         * "sivaprasadreddy.k@gmail.com");
         * 
         * user1.addRoles(role1, role2); user2.addRoles(role1);
         * 
         * userService.createUser(user1); userService.createUser(user2);
         */
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

import com.mongodb.MongoClient;
import guice.module.SpringModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import javax.inject.Inject;
import java.net.UnknownHostException;

/**
 * @author Mohit Sinha
 */
public class SpringDataMongoModule extends SpringModule{

    /**
     * Sync the context lifecycle with Play's.
     */
    @Inject
    public SpringDataMongoModule() {
        super((DefaultListableBeanFactory)new AnnotationConfigApplicationContext(SpringDataMongoConfiguration.class).getAutowireCapableBeanFactory());
    }

    /**
     * This configuration establishes Spring Data concerns including those of Repositories.
     */

    @Configuration
    @PropertySource("classpath:config.properties")
    @EnableMongoRepositories("repositories")
    public static class SpringDataMongoConfiguration {

        @Value("${mongodb.url}")
        private String mongodbUrl;

        @Value("${mongodb.port}")
        private Integer mongodbPort;

        @Value("${mongodb.database}")
        private String mongodbDatabase;

        @Bean
        public static PropertySourcesPlaceholderConfigurer propertyConfigIn() {
            return new PropertySourcesPlaceholderConfigurer();
        }

        @Bean
        public MongoDbFactory mongoDbFactory() throws UnknownHostException {
            return new SimpleMongoDbFactory(new MongoClient(mongodbUrl, mongodbPort), mongodbDatabase);
        }

        @Bean
        public MongoTemplate mongoTemplate() throws UnknownHostException{
            MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
            return mongoTemplate;
        }
    }
}

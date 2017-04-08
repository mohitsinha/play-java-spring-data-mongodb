import com.mongodb.MongoClient;
import guice.module.SpringModule;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
    @EnableMongoRepositories("repositories")
    public static class SpringDataMongoConfiguration {

        @Bean
        public MongoDbFactory mongoDbFactory() throws UnknownHostException {
            return new SimpleMongoDbFactory(new MongoClient(),"play");
        }

        @Bean
        public MongoTemplate mongoTemplate() throws UnknownHostException{
            MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
            return mongoTemplate;
        }
    }
}

package com.example.hmsapi.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
@EnableTransactionManagement
public class MongoTemplateConfiguration extends AbstractMongoClientConfiguration {
	 	@Value("${spring.data.mongodb.uri}")
	    private String dbUri;
	    @Bean
	    MongoTransactionManager transactionManager(MongoDatabaseFactory dbFactory) {
	        return new MongoTransactionManager(dbFactory);
	    }

	    @Override
	    protected String getDatabaseName() {
	        return "hmsdb";
	    }
	    @Override
	    public MongoClient mongoClient() {
	        final ConnectionString connectionString = new ConnectionString(dbUri);
	        final MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
	                .applyConnectionString(connectionString)
	                .build();
	        return MongoClients.create(mongoClientSettings);
	    }
}

/*Configure from scratch without using AbstractMongoClientConfiguration*/
//@Configuration
//public class SimpleMongoConfig {
// 
//    @Bean
//    public MongoClient mongo() {
//        ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017/test");
//        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
//          .applyConnectionString(connectionString)
//          .build();
//        
//        return MongoClients.create(mongoClientSettings);
//    }
//
//    @Bean
//    public MongoTemplate mongoTemplate() throws Exception {
//        return new MongoTemplate(mongo(), "test");
//    }
//}

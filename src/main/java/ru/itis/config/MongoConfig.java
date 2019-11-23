package ru.itis.config;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

@Configuration
public class MongoConfig extends AbstractMongoConfiguration {
    @Override
    public MongoClient mongoClient() {
        return new MongoClient("ds039211.mlab.com", 39211);
    }

    @Override
    protected String getDatabaseName() {
        return "wenwo";
    }
}

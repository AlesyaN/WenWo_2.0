package ru.itis.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

@Configuration
public class MongoConfig extends AbstractMongoConfiguration {
    @Override
    public MongoClient mongoClient() {
        MongoClientURI uri = new MongoClientURI("mongodb://mongo:qwerty007@ds039211.mlab.com:39211/heroku_0c1rj4kf");
        return new MongoClient(uri);
    }

    @Override
    protected String getDatabaseName() {
        return "heroku_0c1rj4kf";
    }
}

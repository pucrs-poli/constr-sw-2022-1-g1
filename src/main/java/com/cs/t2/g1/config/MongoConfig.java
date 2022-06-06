package com.cs.t2.g1.config;

import com.cs.t2.g1.models.Building;
import com.cs.t2.g1.models.Classrooms;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.UuidRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;

@Configuration
public class MongoConfig {

    @Value("${spring.data.mongodb.uri}")
    private String mongoUri;

    @Value("${spring.data.mongodb.database}")
    private String mongoDb;

    @Bean
    public MongoClient mongoClient() {
        ConnectionString connectionString = new ConnectionString(mongoUri);
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .uuidRepresentation(UuidRepresentation.STANDARD)
                .build();


        return MongoClients.create(mongoClientSettings);
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        MongoTemplate mt = new MongoTemplate(mongoClient(), mongoDb);
        mt.indexOps(Building.class)
                .ensureIndex(new Index("buildingNumber", Sort.Direction.ASC).unique());
        mt.indexOps(Classrooms.class)
                .ensureIndex(new Index("classroomNumber", Sort.Direction.ASC).unique());

        return mt;
    }
}

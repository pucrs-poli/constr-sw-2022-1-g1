package com.cs.t2.g1.repository;

import com.cs.t2.g1.models.Classrooms;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;
import java.util.Optional;

@EnableMongoRepositories
public interface ClassroomRepository extends MongoRepository<Classrooms, String> {
    Optional<Classrooms> findById(String classUuid);
    Classrooms save(Classrooms classrooms);
    void deleteById(String classUuid);
    List<Classrooms> findAllByBuildingUuid(String buildingUuid);

    @Query("{buildingUuid: ?0}")
    List<Classrooms> getAllByBuildingUuid(String buildingUuid);
}

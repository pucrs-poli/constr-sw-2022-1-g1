package com.cs.t2.g1.repository;

import com.cs.t2.g1.models.Building;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;
import java.util.Optional;

@EnableMongoRepositories
public interface BuildingRepository extends MongoRepository<Building, String> {
    List<Building> findAllByEnabled(boolean enabled);
    Optional<Building> findById(String uuid);
    Building save(Building building);
    void deleteBuildingById(String uuid);

    @Query("{id :?0}")
    Building getById(String id);
}

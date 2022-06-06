package com.cs.t2.g1.service;

import com.cs.t2.g1.models.Building;
import com.cs.t2.g1.models.Classrooms;
import com.cs.t2.g1.repository.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildingService {

    @Autowired
    private BuildingRepository buildingRepository;

    public Building saveBuilding(Building building) {
        return buildingRepository.save(building);
    }

    public List<Building> getBuildings(){
       return buildingRepository.findAllByEnabled(true);
    }

    public void deleteBuilding(String uuid){
        buildingRepository.deleteBuildingById(uuid);
    }

    public Building getBuilding(String uuid) {
        return buildingRepository.findById(uuid).orElse(null);
    }

    public Building getBuildingByUuid(String buildingUuid) {
        return buildingRepository.getById(buildingUuid);
    }
}

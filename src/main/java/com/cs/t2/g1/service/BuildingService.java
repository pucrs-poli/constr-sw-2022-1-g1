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
        List<Classrooms> classrooms = building.getClassrooms();
        String buildingUuid = building.getId();

        for(Classrooms classroom : classrooms)
            classroom.setBuildingUuid(buildingUuid);

        return buildingRepository.save(building);
    }
    public Building updateBuilding(Building building, String buildingUuid) {
        if(buildingRepository.getById(buildingUuid) == null) return null;
        building.setId(buildingUuid);
        return buildingRepository.save(building);
    }

    public Building refreshBuilding(Building buildingPatch, String buildingUuid) {
        Building building = buildingRepository.getById(buildingUuid);
        if(building == null) return null;

        if(buildingPatch.getBuildingName() != null)
            building.setBuildingName(buildingPatch.getBuildingName());

        if(buildingPatch.getBuildingDescription() != null)
            building.setBuildingDescription(buildingPatch.getBuildingDescription());

        if(buildingPatch.getBuildingNumber() != null)
            building.setBuildingNumber(buildingPatch.getBuildingNumber());

        if(buildingPatch.getEnabled() != null)
            building.setEnabled(buildingPatch.getEnabled());


        return buildingRepository.save(building);
    }

    public List<Building> getBuildings(){
       return buildingRepository.findAllByEnabled(true);
    }

    public Building deleteBuilding(String uuid){
        Building build = buildingRepository.findById(uuid).orElse(null);
        if(build != null) {
            build.setEnabled(false);
            saveBuilding(build);
        }
        return build;
    }

    public Building getBuilding(String uuid) {
        return buildingRepository.findById(uuid).orElse(null);
    }

    public Building getBuildingWithQuery(String uuid) {
        return buildingRepository.getById(uuid);
    }
}

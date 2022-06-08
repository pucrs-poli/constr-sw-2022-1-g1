package com.cs.t2.g1.controllers;

import com.cs.t2.g1.models.Building;
import com.cs.t2.g1.models.Classrooms;
import com.cs.t2.g1.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController("/buildings")
public class BuildingController {

    @Autowired
    private BuildingService buildingService;

    @PostMapping(
            value = "/buildings",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity saveBuilding(@RequestBody Building building) {
        Building dbuilding = null;
        try {
            dbuilding = buildingService.saveBuilding(building);
        } catch (Exception e) {
            System.out.println(e.getClass());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(dbuilding, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/buildings/{uuid}")
    public ResponseEntity deleteBuildings( @PathVariable("uuid") String uuid) {
        if(buildingService.deleteBuilding(uuid) == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.noContent().build();
    }

    @GetMapping(
            value = "/buildings",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<List<Building>> getBuildings() {
        List<Building> building = buildingService.getBuildings();
        return ResponseEntity.ok(building);
    }

    @GetMapping(
            value = "/buildings/query",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Building> getBuildings2(@RequestParam("uuid") String uuid) {
        Building building = buildingService.getBuildingWithQuery(uuid);
        if(building == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(building);
    }

    @GetMapping(
            value = "/buildings/{uuid}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Building> getBuilding(@PathVariable("uuid") String uuid) {
        Building building = buildingService.getBuilding(uuid);

        if (building != null) {
            return ResponseEntity.ok(building);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping(
            value = "/buildings/{buildingUuid}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity updateBuilding(
            @RequestBody Building building,
            @PathVariable("buildingUuid") String buildingUuid
    ) {
        if (buildingService.updateBuilding(building, buildingUuid) != null)
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping(
            value = "/buildings/{buildingUuid}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity refreshBuilding(
            @RequestBody Building building,
            @PathVariable("buildingUuid") String buildingUuid
    ) {
        if (buildingService.refreshBuilding(building, buildingUuid) != null)
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}

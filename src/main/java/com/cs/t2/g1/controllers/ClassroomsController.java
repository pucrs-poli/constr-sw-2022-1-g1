package com.cs.t2.g1.controllers;

import com.cs.t2.g1.models.Classrooms;
import com.cs.t2.g1.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClassroomsController {

    @Autowired
    private ClassroomService classroomService;

    @GetMapping(
            value = "/buildings/{buildingUuid}/classrooms",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<List<Classrooms>> getClassrooms(@PathVariable("buildingUuid") String buildingUuid) {
        List<Classrooms> classrooms = classroomService.getClassrooms(buildingUuid);
        return ResponseEntity.ok(classrooms);
    }

    @GetMapping(
            value = "/buildings/classrooms/query",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<List<Classrooms>> getClassroomsByBuildingNumber(@RequestParam("uuid") String buildingUuid) {
        List<Classrooms> classrooms = classroomService.getClassroomsByBuildingUuid(buildingUuid);
        return ResponseEntity.notFound().build();
    }

    @GetMapping(
            value = "/buildings/{buildingNumber}/classrooms/{classNumber}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Classrooms> getClassroom(@PathVariable("classNumber") String classUuid, @PathVariable String buildingNumber) {
        Classrooms classroom = classroomService.getClassroom(classUuid);

        if (classroom != null) {
            return ResponseEntity.ok(classroom);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(
            value = "/buildings/{buildingUuid}/classrooms",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity saveClassroom(@RequestBody Classrooms classroom, @PathVariable("buildingUuid") String buildingUuid) {
        Classrooms dclassroom = null;
        try {
            dclassroom = classroomService.saveClassroom(classroom, buildingUuid);
        } catch (Exception e) {
            System.out.println(e.getClass());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(dclassroom, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/buildings/{buildingUuid}/classrooms/{classUuid}")
    public ResponseEntity deleteClassrooms( @PathVariable("buildingUuid") String buildingUuid, @PathVariable("classUuid") String classUuid) {
        if(classroomService.deleteClassroom(buildingUuid, classUuid) == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.noContent().build();
    }

    @PutMapping(
            value = "/buildings/{buildingUuid}/classrooms/{classroomUuid}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity updateClassroom(
            @RequestBody Classrooms classroom,
            @PathVariable("buildingUuid") String buildingUuid,
            @PathVariable("classroomUuid") String classroomUuid
    ) {
        if (classroomService.updateClassroom(classroom, classroomUuid, buildingUuid) != null)
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping(
            value = "/buildings/{buildingUuid}/classrooms/{classroomUuid}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity refreshClassroom(
            @RequestBody Classrooms classroom,
            @PathVariable("buildingUuid") String buildingUuid,
            @PathVariable("classroomUuid") String classroomUuid
    ) {
        if (classroomService.refreshClassroom(classroom, classroomUuid, buildingUuid) != null)
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

package com.cs.t2.g1.service;

import com.cs.t2.g1.models.Classrooms;
import com.cs.t2.g1.repository.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassroomService {

    @Autowired
    private ClassroomRepository classroomRepository;

    public Classrooms saveClassroom(Classrooms classrooms, String buildingUuid) {
        classrooms.setBuildingUuid(buildingUuid);
        return classroomRepository.save(classrooms);
    }

    public List<Classrooms> getClassrooms(String buildingUuid){
        return classroomRepository.findAllByBuildingUuid(buildingUuid);
    }

    public void deleteClassroom(String classUuid) {
        classroomRepository.deleteById(classUuid);
    }

    public Classrooms getClassroom(String classUuid) {
        return classroomRepository.findById(classUuid).orElse(null);
    }

    public List<Classrooms> getClassroomsByBuildingUuid(String buildingUuid) {
        return classroomRepository.getAllByBuildingUuid(buildingUuid);
    }
}

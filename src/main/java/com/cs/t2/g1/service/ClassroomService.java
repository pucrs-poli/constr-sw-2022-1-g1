package com.cs.t2.g1.service;

import com.cs.t2.g1.models.Building;
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

    public Classrooms updateClassroom(Classrooms classrooms, String classroomUuid , String buildingUuid) {
        if(classroomRepository.findById(classroomUuid).isEmpty()) return null;

        classrooms.setBuildingUuid(buildingUuid);
        classrooms.setId(classroomUuid);
        return classroomRepository.save(classrooms);
    }

    public Classrooms refreshClassroom(Classrooms classroomsPatch, String classroomUuid , String buildingUuid) {
        Classrooms classroom = classroomRepository.findById(classroomUuid).orElse(null);
        if(classroom == null)return null;
        if(!classroom.getBuildingUuid().equals(buildingUuid)) return null;

        if (classroomsPatch.getClassroomNumber() != null)
            classroom.setClassroomNumber(classroomsPatch.getClassroomNumber());
        if (classroomsPatch.getClassroomName() != null)
            classroom.setClassroomName(classroomsPatch.getClassroomName());
        if (classroomsPatch.getCapacity() != null)
            classroom.setCapacity(classroomsPatch.getCapacity());
        if (classroomsPatch.getEnabled() != null)
            classroom.setEnabled(classroomsPatch.getEnabled());
        if (classroomsPatch.getBuildingUuid() != null)
            classroom.setBuildingUuid(classroomsPatch.getBuildingUuid());

        return classroomRepository.save(classroom);
    }

    public List<Classrooms> getClassrooms(String buildingUuid) {
        return classroomRepository.findAllByBuildingUuid(buildingUuid);
    }

    public Classrooms deleteClassroom(String buildingUuid, String uuid){
        Classrooms classroom = classroomRepository.findById(uuid).orElse(null);
        if(classroom != null) {
            classroom.setEnabled(false);
            saveClassroom(classroom, buildingUuid);
        }
        return classroom;
    }

    public Classrooms getClassroom(String classUuid) {
        return classroomRepository.findById(classUuid).orElse(null);
    }

    public List<Classrooms> getClassroomsByBuildingUuid(String buildingUuid) {
        return classroomRepository.getAllByBuildingUuid(buildingUuid);
    }
}

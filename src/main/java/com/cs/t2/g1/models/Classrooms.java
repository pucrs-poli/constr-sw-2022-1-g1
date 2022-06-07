package com.cs.t2.g1.models;

import com.cs.t2.g1.utils.UUIDHandler;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Setter
@Getter
@AllArgsConstructor
public class Classrooms {

    @Id
    private String id;

    private Integer classroomNumber;
    private String classroomName;
    private Integer capacity;
    private Boolean enabled;

    private String buildingUuid;


    public Classrooms() {
        this.id = UUIDHandler.generateUuid();
    }
}

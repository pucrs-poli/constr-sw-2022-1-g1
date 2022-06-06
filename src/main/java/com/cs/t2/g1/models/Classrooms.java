package com.cs.t2.g1.models;

import com.cs.t2.g1.utils.UUIDHandler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Setter
@Getter
@AllArgsConstructor
public class Classrooms {

    @Id
    private String id;

    private byte classroomNumber;
    private byte capacity;
    private boolean enabled;
    private String buildingUuid;

    public Classrooms() {
        this.id = UUIDHandler.generateUuid();
    }
}

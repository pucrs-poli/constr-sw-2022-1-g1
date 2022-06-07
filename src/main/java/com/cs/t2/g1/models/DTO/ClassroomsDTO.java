package com.cs.t2.g1.models.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
public class ClassroomsDTO {
    private int classroomNumber;
    private String classroomName;
    private int capacity;
    private boolean enabled;
    private String buildingUuid;
}

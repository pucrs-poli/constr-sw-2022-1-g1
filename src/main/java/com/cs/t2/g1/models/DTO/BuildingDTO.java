package com.cs.t2.g1.models.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
public class BuildingDTO {
    private int buildingNumber;
    private String buildingName;
    private String buildingDescription;
    private boolean enabled;
}

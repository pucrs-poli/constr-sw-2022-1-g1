package com.cs.t2.g1.models;

import com.cs.t2.g1.utils.UUIDHandler;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Setter
@Getter
@Document
@AllArgsConstructor
public class Building {

    @Id
    private String id;

    @Indexed(unique=true)
    private Integer buildingNumber;

    private Boolean enabled;

    private String buildingName;
    private String buildingDescription;

    @DBRef
    @DocumentReference(lookup="{'buildingUuid':?#{#self._id} }")
    private List<Classrooms> classrooms;

    public Building() {
        this.id = UUIDHandler.generateUuid();
    }
}
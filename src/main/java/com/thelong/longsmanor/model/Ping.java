package com.thelong.longsmanor.model;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ping {
    @MongoId(value = FieldType.OBJECT_ID)
    private ObjectId _id;
    private String status;
    private String logMessage;
    private LocalDateTime timePinged;
}

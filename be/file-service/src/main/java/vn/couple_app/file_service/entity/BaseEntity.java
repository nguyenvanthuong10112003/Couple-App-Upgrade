package vn.couple_app.file_service.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseEntity {
    @Id
    String id;

    @CreatedDate
    @Field("created_at")
    LocalDateTime createdAt;

    @LastModifiedDate
    @Field("updated_at")
    LocalDateTime updatedAt;

    @Field("status")
    Boolean status = true;
}

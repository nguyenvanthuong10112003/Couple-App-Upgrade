package vn.couple_app.file_service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "image")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Image extends BaseEntity {
    @Field("content")
    byte[] content;

    @Field("user_id")
    String userId;

    @Field("url")
    String url;

    @Field("content_type")
    String contentType;
}
package vn.couple_app.api.entity;


import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Photo extends BaseEntity {
    String photoKey;
    String url;
}
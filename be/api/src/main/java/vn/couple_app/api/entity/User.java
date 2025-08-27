package vn.couple_app.api.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.bouncycastle.asn1.x500.style.RFC4519Style;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends BaseEntity {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", nullable = false, unique = true)
    Account account;

    String fullName;
    String alias;
    LocalDate dob;
    Boolean gender;
    String lifeStory;
    Boolean hadCouple;
    Boolean firstUpdated;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "avatar_id")
    Photo avatar;
}
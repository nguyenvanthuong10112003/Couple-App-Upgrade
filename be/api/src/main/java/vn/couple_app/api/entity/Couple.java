package vn.couple_app.api.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;


@FieldDefaults(level = AccessLevel.PRIVATE)
public class Couple extends BaseEntity {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "user1_id")
    User user1;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "user2_id")
    User user2;

}

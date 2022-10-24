package com.example.scheduleProject.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.Instant;

@Builder
@Entity
@Getter
@Setter
@Table(name = "groups")
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    //@Column(unique = true)
    String name;

    @JsonProperty("created_at")
    Instant createdAt = Instant.now();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    SpecialtyEntity specialties;


}

package com.example.scheduleProject.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Entity
@Setter
@Getter
@Builder
@Table(name = "day")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DayEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String dayOfWeek;

    @Builder.Default
    @JsonProperty("created_at")
    Instant createdAt = Instant.now();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    ScheduleEntity schedules;

    //@OneToMany(fetch = FetchType.EAGER, mappedBy = "days")
    @OneToMany(mappedBy = "days", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    //@OneToMany
    List<SubjectEntity> subjects = new ArrayList<>();

}

package com.example.scheduleProject.domain.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@Table(name = "subject")
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    //@Column(unique = true)
    String name;

    /** Practice or Lection **/
    String lesson;

    String teacher;

    String location;

    //@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "days_id")
    DayEntity days;

}

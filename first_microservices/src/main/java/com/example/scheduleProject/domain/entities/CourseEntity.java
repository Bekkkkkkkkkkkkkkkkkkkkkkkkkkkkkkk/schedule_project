package com.example.scheduleProject.domain.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

@Builder
@Getter
@Setter
@Entity
@Table(name = "course")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    /**Проверяет и выдает исключение при дубликатах названии курса**/
    //@Column(unique = true)
    String courseIdentity;

    //@Builder.Default
    @OneToMany(orphanRemoval = true, fetch = FetchType.EAGER, mappedBy = "course")
    ///@JoinColumn(name = "coursed") нужен для того чтобы добавить course id в таблицу specialties
    List<SpecialtyEntity> specialties;
}

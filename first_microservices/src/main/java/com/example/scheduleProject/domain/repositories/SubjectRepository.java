package com.example.scheduleProject.domain.repositories;

import com.example.scheduleProject.domain.entities.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.stream.Stream;

public interface SubjectRepository extends JpaRepository<SubjectEntity, Long> {

    Optional<SubjectEntity> findByName(String name);
    Stream<SubjectEntity> streamAllBy();
    Stream<SubjectEntity> streamAllByNameStartsWithIgnoreCase(String prefixName);

}

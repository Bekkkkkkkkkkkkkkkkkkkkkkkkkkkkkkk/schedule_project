package com.example.scheduleProject.domain.repositories;

import com.example.scheduleProject.domain.entities.DayEntity;
import com.example.scheduleProject.domain.entities.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.stream.Stream;

public interface DayRepository extends JpaRepository<DayEntity, Long> {
    Optional<DayEntity> findByDayOfWeek(String day);
    Stream<DayEntity> streamAllBy();
    Stream<DayEntity> streamAllByDayOfWeekStartsWithIgnoreCase(String prefixName);
}

package com.example.scheduleProject.domain.repositories;

import com.example.scheduleProject.domain.entities.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {
}

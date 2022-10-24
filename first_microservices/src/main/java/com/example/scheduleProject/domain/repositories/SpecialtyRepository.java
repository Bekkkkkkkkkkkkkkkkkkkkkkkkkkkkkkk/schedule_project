package com.example.scheduleProject.domain.repositories;

import com.example.scheduleProject.domain.entities.SpecialtyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialtyRepository extends JpaRepository<SpecialtyEntity, Long> {
}

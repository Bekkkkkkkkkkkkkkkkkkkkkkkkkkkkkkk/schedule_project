package com.example.scheduleProject.domain.repositories;

import com.example.scheduleProject.domain.entities.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<GroupEntity, Long> {
}

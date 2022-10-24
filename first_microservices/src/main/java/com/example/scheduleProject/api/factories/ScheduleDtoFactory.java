package com.example.scheduleProject.api.factories;

import com.example.scheduleProject.api.dto.ScheduleDto;
import com.example.scheduleProject.domain.entities.ScheduleEntity;
import org.springframework.stereotype.Component;

@Component
public class ScheduleDtoFactory {

    public ScheduleDto makeScheduleDto(ScheduleEntity entity){

        return ScheduleDto.builder()
                .id(entity.getId())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}

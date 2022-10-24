package com.example.scheduleProject.api.factories;

import com.example.scheduleProject.api.dto.SpecialtyDto;
import com.example.scheduleProject.domain.entities.SpecialtyEntity;
import org.springframework.stereotype.Component;

@Component
public class SpecialtyDtoFactory {

    public SpecialtyDto makeSpecialtyDto(SpecialtyEntity entity){

        return SpecialtyDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}

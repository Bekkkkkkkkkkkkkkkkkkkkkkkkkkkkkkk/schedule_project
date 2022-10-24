package com.example.scheduleProject.api.factories;

import com.example.scheduleProject.api.dto.GroupDto;
import com.example.scheduleProject.domain.entities.GroupEntity;
import org.springframework.stereotype.Component;

@Component
public class GroupDtoFactory {

    public GroupDto makeGroupDto(GroupEntity entity) {

        return GroupDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}

package com.example.scheduleProject.api.factories;

import com.example.scheduleProject.api.dto.SubjectDto;
import com.example.scheduleProject.domain.entities.SubjectEntity;
import org.springframework.stereotype.Component;

@Component
public class SubjectDtoFactory {

    public SubjectDto makeSubjectDto(SubjectEntity entity){

        return SubjectDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .lesson(entity.getLesson())
                .teacher(entity.getTeacher())
                .location(entity.getLocation())
                .build();
    }
}

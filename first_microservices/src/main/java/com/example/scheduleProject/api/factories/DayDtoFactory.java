package com.example.scheduleProject.api.factories;

import com.example.scheduleProject.api.dto.DayDto;
import com.example.scheduleProject.domain.entities.DayEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class DayDtoFactory {

    SubjectDtoFactory subjectDtoFactory;

    public DayDto makeDayDto(DayEntity entity) {

        return DayDto.builder()
                .id(entity.getId())
                .createdAt(entity.getCreatedAt())
                .dayOfWeek(entity.getDayOfWeek())
                .subjectDtoList(entity
                        .getSubjects()
                        .stream()
                        .map(subjectDtoFactory::makeSubjectDto)
                        .collect(Collectors.toList()))
                .build();
    }
}

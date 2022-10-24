package com.example.scheduleProject.api.factories;

import com.example.scheduleProject.api.dto.CourseDto;
import com.example.scheduleProject.domain.entities.CourseEntity;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class CourseDtoFactory {

    SpecialtyDtoFactory specialtyDtoFactory;

    public CourseDto makeCourseDto(CourseEntity entity) {

        return CourseDto.builder()
                .id(entity.getId())
                .courseIdentity(entity.getCourseIdentity())
                .specialties(
                        entity
                                .getSpecialties()
                                .stream()
                                .map(specialtyDtoFactory::makeSpecialtyDto)
                                .collect(Collectors.toList())
                )
                .build();
    }
}

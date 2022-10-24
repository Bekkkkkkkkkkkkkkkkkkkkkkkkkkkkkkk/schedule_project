package com.example.scheduleProject.api.controllers;

import com.example.scheduleProject.api.controllers.helper.ControllerHelper;
import com.example.scheduleProject.api.dto.CourseDto;
import com.example.scheduleProject.api.dto.SpecialtyDto;
import com.example.scheduleProject.api.exceptions.BadRequestException;
import com.example.scheduleProject.api.factories.CourseDtoFactory;
import com.example.scheduleProject.domain.entities.CourseEntity;
import com.example.scheduleProject.domain.repositories.CourseRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
@RestController
public class CourseController {

    CourseRepository courseRepository;
    CourseDtoFactory courseDtoFactory;
    ControllerHelper controllerHelper;
    //Logger logger;

    public static final String CREATE_COURSE = "/api/courses/create";

    @PostMapping(CREATE_COURSE)
    public CourseDto createCourse(
            @RequestParam String courseIdentity){

        if (courseIdentity.trim().isEmpty()){
            throw new BadRequestException("Course Identity can not be empty");
        }

        CourseEntity course = courseRepository.saveAndFlush(
                CourseEntity.builder()
                        .courseIdentity(courseIdentity)
                        .build()
        );

        return courseDtoFactory.makeCourseDto(course);
    }
}

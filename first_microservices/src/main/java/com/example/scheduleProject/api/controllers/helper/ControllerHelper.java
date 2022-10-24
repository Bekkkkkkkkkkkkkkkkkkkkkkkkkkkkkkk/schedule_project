package com.example.scheduleProject.api.controllers.helper;

import com.example.scheduleProject.api.exceptions.NotFoundException;
import com.example.scheduleProject.domain.entities.DayEntity;
import com.example.scheduleProject.domain.entities.SubjectEntity;
import com.example.scheduleProject.domain.repositories.DayRepository;
import com.example.scheduleProject.domain.repositories.SubjectRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Component
@Transactional
public class ControllerHelper {
    SubjectRepository subjectRepository;
    DayRepository dayRepository;

    public SubjectEntity getSubjectOrThrowExceptionId(Long subjectId) {

        return subjectRepository
                .findById(subjectId)
                .orElseThrow(() ->
                        new NotFoundException(
                                String.format(
                                        "Subject with \"%s\" doesn't exist.",
                                        subjectId
                                )
                        )
                );
    }
    public DayEntity getDayOrThrowExceptionId(Long dayId) {

        return dayRepository
                .findById(dayId)
                .orElseThrow(() ->
                        new NotFoundException(
                                String.format(
                                        "Day with \"%s\" doesn't exist.",
                                        dayId
                                )
                        )
                );
    }
}

package com.example.scheduleProject.api.controllers;

import com.example.scheduleProject.api.controllers.helper.ControllerHelper;
import com.example.scheduleProject.api.dto.AskDto;
import com.example.scheduleProject.api.dto.SubjectDto;
import com.example.scheduleProject.api.exceptions.BadRequestException;
import com.example.scheduleProject.api.factories.SubjectDtoFactory;
import com.example.scheduleProject.domain.entities.SubjectEntity;
import com.example.scheduleProject.domain.repositories.SubjectRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
@RestController
public class SubjectController {

    SubjectRepository subjectRepository;
    SubjectDtoFactory subjectDtoFactory;
    ControllerHelper controllerHelper;

    public static final String CREATE_SUBJECT = "/api/subjects/post";
    public static final String FETCH_SUBJECT = "/api/subjects/";
    public static final String EDIT_SUBJECT = "/api/subjects/edit/{subject_id}";
    public static final String CREATE_OR_UPDATE_SUBJECT = "/api/subjects/cous/";
    public static final String DELETE_SUBJECT = "/api/subjects/delete/{subject_id}";

    @GetMapping(FETCH_SUBJECT)
    public List<SubjectDto> fetchSubject(
            @RequestParam(value = "prefix_name", required = false)Optional<String> optionalPrefixName){

        optionalPrefixName = optionalPrefixName.filter(prefixName -> !prefixName.trim().isEmpty());

        Stream<SubjectEntity> subjectStream = optionalPrefixName
                .map(subjectRepository::streamAllByNameStartsWithIgnoreCase)
                .orElseGet(subjectRepository::streamAllBy);

        return subjectStream
                .map(subjectDtoFactory::makeSubjectDto)
                .collect(Collectors.toList());
    }

    @PutMapping(CREATE_OR_UPDATE_SUBJECT)
    public SubjectDto createOrUpdateSubject(
            @RequestParam(value = "subject_id", required = false) Optional<Long> optionalSubjectId,
            @RequestParam(value = "subject_name", required = false) Optional<String> optionalSubjectName,
            @RequestParam(value = "subject_lesson", required = false) Optional<String> optionalSubjectLesson,
            @RequestParam(value = "subject_teacher", required = false) Optional<String> optionalSubjectTeacher,
            @RequestParam(value = "subject_location", required = false) Optional<String> optionalSubjectLocation) {

        optionalSubjectName = optionalSubjectName.filter(subjectName -> !subjectName.trim().isEmpty());
        optionalSubjectLesson = optionalSubjectLesson.filter(subjectLesson -> !subjectLesson.trim().isEmpty());
        optionalSubjectTeacher = optionalSubjectTeacher.filter(subjectTeacher -> !subjectTeacher.trim().isEmpty());
        optionalSubjectLocation = optionalSubjectLocation.filter(subjectLocation -> !subjectLocation.trim().isEmpty());

        boolean isCreate = optionalSubjectId.isEmpty();

        if (isCreate && optionalSubjectName.isEmpty()) {
            throw new BadRequestException("Subject name can't be empty.");
        }
        if (isCreate && optionalSubjectLesson.isEmpty()) {
            throw new BadRequestException("Subject lesson can't be empty.");
        }
        if (isCreate && optionalSubjectTeacher.isEmpty()) {
            throw new BadRequestException("Subject teacher can't be empty.");
        }
        if (isCreate && optionalSubjectLocation.isEmpty()) {
            throw new BadRequestException("Subject location can't be empty.");
        }

        final SubjectEntity subject = optionalSubjectId
                .map(controllerHelper::getSubjectOrThrowExceptionId)
                .orElseGet(() -> SubjectEntity.builder().build());

        optionalSubjectName
                .ifPresent(subjectName -> {
                    subjectRepository
                            .findByName(subjectName)
                            .filter(anotherSubject -> !Objects.equals(anotherSubject.getId(), subject.getId()))
                            .ifPresent(anotherProject -> {
                                throw new BadRequestException(
                                        String.format("Subject \"%s\" already exists.", subjectName)
                                );
                            });

                    subject.setName(subjectName);
                });

        optionalSubjectLesson
                .ifPresent(subject::setLesson);

        optionalSubjectTeacher
                .ifPresent(subject::setTeacher);

        optionalSubjectLocation
                .ifPresent(subject::setLocation);

        final SubjectEntity savedSubject = subjectRepository.saveAndFlush(subject);

        return subjectDtoFactory.makeSubjectDto(savedSubject);
    }

    @PostMapping(CREATE_SUBJECT)
    public SubjectDto createSubject(
            @RequestParam String name,
            @RequestParam String lesson,
            @RequestParam String location,
            @RequestParam String teacher){

        if(name.trim().isEmpty()){
            throw new BadRequestException("Name can't be empty.");
        }

        subjectRepository
                .findByName(name)
                .ifPresent(subject -> {
                    throw new BadRequestException(
                            String.format(
                                    "Subject \"%s\" already exist.",
                                    name
                            )
                    );
                });

        SubjectEntity subject = subjectRepository.saveAndFlush(
                SubjectEntity.builder()
                .name(name)
                .lesson(lesson)
                .location(location)
                .teacher(teacher)
                .build()
        );
        return subjectDtoFactory.makeSubjectDto(subject);
    }

    @PatchMapping(EDIT_SUBJECT)
    public SubjectDto editSubject(
            @PathVariable("subject_id") Long subjectId,
            @RequestParam String name,
            @RequestParam String lesson,
            @RequestParam String location,
            @RequestParam String teacher) {

        if(name.trim().isEmpty()){
            throw new BadRequestException("Name field can't be empty.");
        }
        if(lesson.trim().isEmpty()){
            throw new BadRequestException("Lesson field can't be empty.");
        }
        if(location.trim().isEmpty()){
            throw new BadRequestException("Location field can't be empty.");
        }
        if(teacher.trim().isEmpty()){
            throw new BadRequestException("Teacher field can't be empty.");
        }

        SubjectEntity subject = controllerHelper.getSubjectOrThrowExceptionId(subjectId);

        subjectRepository
                .findByName(name)
                .filter(anotherSubject -> !Objects.equals(anotherSubject.getId(), subjectId))
                .ifPresent(anotherSubject -> {
                    throw new BadRequestException(String.format("Subject \"%s\" already exist.", name));
                });

        subject.setName(name);
        subject.setLesson(lesson);
        subject.setLocation(location);
        subject.setTeacher(teacher);

        subject = subjectRepository.saveAndFlush(subject);

        return subjectDtoFactory.makeSubjectDto(subject);
    }

    @DeleteMapping(DELETE_SUBJECT)
    public AskDto deleteSubject(
            @PathVariable("subject_id") Long subjectId){

        controllerHelper.getSubjectOrThrowExceptionId(subjectId);

        subjectRepository.deleteById(subjectId);

        return AskDto.makeDefault(true);
    }

    /*private SubjectEntity getSubjectOrThrowException(Long subjectId) {
        return subjectRepository
                .findById(subjectId)
                .orElseThrow(() ->
                        new NotFoundException(
                                String.format(
                                        "Project with \"$s\" doesn't exist.",
                                        subjectId
                                )
                        )
                );
    }*/
}

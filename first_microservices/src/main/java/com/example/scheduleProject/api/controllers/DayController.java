package com.example.scheduleProject.api.controllers;

import com.example.scheduleProject.api.controllers.helper.ControllerHelper;
import com.example.scheduleProject.api.dto.AskDto;
import com.example.scheduleProject.api.dto.DayDto;
import com.example.scheduleProject.api.exceptions.BadRequestException;
import com.example.scheduleProject.api.factories.DayDtoFactory;
import com.example.scheduleProject.domain.entities.DayEntity;
import com.example.scheduleProject.domain.entities.SubjectEntity;
import com.example.scheduleProject.domain.repositories.DayRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
@RestController
public class DayController {

    DayRepository dayRepository;

    DayDtoFactory dayDtoFactory;

    ControllerHelper controllerHelper;

    private final static String CREATE_DAY = "/api/day-create";
    private final static String GET_DAY = "/api/day-get/";
    private final static String EDIT_DAY = "/api/day-edit/{day_id}";
    private final static String DELETE_DAY = "/api/day-delete/{day_id}";

    @GetMapping(GET_DAY)
    public List<DayDto> getDay(@RequestParam(value = "prefix_name", required = false) Optional<String> optionalPrefixName){

        optionalPrefixName = optionalPrefixName.filter(name -> !name.trim().isEmpty());

        Stream<DayEntity> dayEntityStream = optionalPrefixName
                .map(dayRepository::streamAllByDayOfWeekStartsWithIgnoreCase)
                .orElseGet(dayRepository::streamAllBy);

        return dayEntityStream
                .map(dayDtoFactory::makeDayDto)
                .collect(Collectors.toList());
    }

    @PostMapping(CREATE_DAY)
    public DayDto createDay(@RequestParam String dayOfWeak){

        if(dayOfWeak.trim().isEmpty()){
            throw new BadRequestException("Day of week can not be empty!");
        }

        DayEntity entity = dayRepository.saveAndFlush(
                DayEntity
                        .builder()
                        .dayOfWeek(dayOfWeak)
                        .build()
        );

        return dayDtoFactory.makeDayDto(entity);

    }

    @PatchMapping(EDIT_DAY)
    public DayDto editDay(
            @PathVariable("day_id") Long dayId,
            @RequestParam String dayOfWeak){

        if(dayOfWeak.trim().isEmpty()){
            throw new BadRequestException("Day of weak can not be empty!");
        }

        DayEntity dayEntity = controllerHelper.getDayOrThrowExceptionId(dayId);

        dayRepository
                .findByDayOfWeek(dayOfWeak)
                .filter(anotherDay -> !Objects.equals(anotherDay.getId(), dayId))
                .ifPresent(anotherDay -> {
                    throw new BadRequestException(String.format("Day \"%s\" already exist.", dayOfWeak));
                });

        dayEntity.setDayOfWeek(dayOfWeak);

        dayEntity = dayRepository.saveAndFlush(dayEntity);

        return dayDtoFactory.makeDayDto(dayEntity);

    }

    @DeleteMapping(DELETE_DAY)
    public AskDto deleteDay(@PathVariable("day_id") Long dayId){

        controllerHelper.getDayOrThrowExceptionId(dayId);

        dayRepository.deleteById(dayId);

        return AskDto.makeDefault(true);

    }

}
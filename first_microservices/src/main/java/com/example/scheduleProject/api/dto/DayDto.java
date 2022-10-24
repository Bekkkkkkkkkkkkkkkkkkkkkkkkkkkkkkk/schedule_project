package com.example.scheduleProject.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class DayDto {

    @NonNull
    Long id;

    @JsonProperty("created_at")
    @CreatedDate
    Instant createdAt;

    @JsonProperty("day_of_week")
    @NonNull
    String dayOfWeek;

    @NonNull
    List<SubjectDto> subjectDtoList;
}

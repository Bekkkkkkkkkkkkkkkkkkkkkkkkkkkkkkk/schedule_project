package com.example.scheduleProject.api.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto {

    @NonNull
    Long id;

    @NonNull
    String courseIdentity;

    @NonNull
    List<SpecialtyDto> specialties;
}

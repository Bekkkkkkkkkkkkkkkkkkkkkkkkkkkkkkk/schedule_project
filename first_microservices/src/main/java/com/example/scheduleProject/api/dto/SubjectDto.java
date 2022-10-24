package com.example.scheduleProject.api.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDto {

    @NonNull
    Long id;

    @NonNull
    String name;

    /** Practice or Lection **/
    @NonNull
    String lesson;

    @NonNull
    String teacher;

    @NonNull
    String location;
}

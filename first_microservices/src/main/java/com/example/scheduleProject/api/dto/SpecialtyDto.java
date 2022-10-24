package com.example.scheduleProject.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.Instant;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class SpecialtyDto {

    @NonNull
    Long id;

    @NonNull
    String name;

    @NonNull
    String description;

    @JsonProperty("created_at")
    @NonNull
    Instant createdAt;
}

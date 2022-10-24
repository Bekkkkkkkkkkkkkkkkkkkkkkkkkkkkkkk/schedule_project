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
public class GroupDto {

    @NonNull
    Long id;

    @NonNull
    String name;

    @JsonProperty("created_at")
    @NonNull
    Instant createdAt;
}

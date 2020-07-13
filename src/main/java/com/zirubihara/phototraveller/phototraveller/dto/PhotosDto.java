package com.zirubihara.phototraveller.phototraveller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhotosDto {
    private Long id;
    private String name;
    private String description;
    private Integer numberOfPosts;
    private Instant createdDate;
}

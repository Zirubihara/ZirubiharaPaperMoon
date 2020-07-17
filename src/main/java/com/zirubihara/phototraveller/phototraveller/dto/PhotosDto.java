package com.zirubihara.phototraveller.phototraveller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhotosDto {
    private Long id;
    @Size(min = 5, max = 40, message = "Nazwa powinna zawierać od 5 do 40 znaków")
    private String name;
    @Size(min = 10, max = 500, message = "Opis powinien zawierać od 10 do 500 zanków")
    private String description;
    private Integer numberOfPosts;
    private Instant createdDate;
}

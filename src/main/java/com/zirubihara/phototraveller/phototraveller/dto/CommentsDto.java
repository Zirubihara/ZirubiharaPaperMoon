package com.zirubihara.phototraveller.phototraveller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentsDto {
    private Long id;
    private Long postId;
    private Instant createdDate;
    @Size(min = 2, max = 200, message = "Treść komentarza powinna mieć od 2 do 200 znaków")
    private String text;
    private String userName;
}

package com.zirubihara.phototraveller.phototraveller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {
    private Long postId;
    @Size(min = 5, max = 40, message = "Nazwa powinna zawierać od 5 do 40 znaków")
    private String photosName;
    @Size(min = 10, max = 500, message = "Nazwa posta powinna zawierać od 10 do 500 znaków")
    private String postName;
    private String url;
    @Size(min = 10, max = 1000, message = "Opis powinien zawierać od 10 do 1000 znaków")
    private String description;
}

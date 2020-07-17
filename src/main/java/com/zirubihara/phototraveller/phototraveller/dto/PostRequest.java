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
    private String photosName;
    @Size(min = 2, max = 30, message = "Nazwa posta powinna zawieraćod 2 do 30 znaków")
    private String postName;
    private String url;
    @Size(min = 10, max = 500, message = "Nazwa opisu powinna mieć od 10 do 500 zanków")
    private String description;
}

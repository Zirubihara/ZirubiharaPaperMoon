package com.zirubihara.phototraveller.phototraveller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String email;
    @Size(min = 4, max = 20, message = "Niepoprawna długość nazwy użytkownika")
    private String username;
    @Size(min = 4, max = 20, message = "Niepoprawna długość hasła")
    private String password;

}

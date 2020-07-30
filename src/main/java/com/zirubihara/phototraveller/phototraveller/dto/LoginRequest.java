package com.zirubihara.phototraveller.phototraveller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginRequest {
    @Size(min = 4, max = 20, message = "Niepoprawna długość nazwy nazwy użtkownika")
    private String username;
    @Size(min = 4, max = 20, message = "Niepoprawna długość hasła")
    private String password;
}

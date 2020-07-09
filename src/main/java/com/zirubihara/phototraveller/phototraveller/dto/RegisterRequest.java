package com.zirubihara.phototraveller.phototraveller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String email;
    //@Size(min = 5, max = 10, message = "XD")
    @Min(6)
    private String username;
    private String password;

}

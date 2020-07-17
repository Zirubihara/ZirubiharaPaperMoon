package com.zirubihara.phototraveller.phototraveller.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
public class ValidationErrorsDetails {
    private Instant validationErrorDate;
    private String message;
    private String details;
}

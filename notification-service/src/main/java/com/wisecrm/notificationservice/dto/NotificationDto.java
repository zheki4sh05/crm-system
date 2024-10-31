package com.wisecrm.notificationservice.dto;

import lombok.*;
import jakarta.validation.constraints.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDto {

    @NotBlank
    @NotNull
    private String subject;

    @NotBlank
    @NotNull
    @Email(message = "uncorrected email value")
    private String mailTo;

    @NotBlank
    @NotNull
    @Email(message = "uncorrected email value")
    private String mailFrom;

    @NotBlank
    @NotNull
    private String body;

}

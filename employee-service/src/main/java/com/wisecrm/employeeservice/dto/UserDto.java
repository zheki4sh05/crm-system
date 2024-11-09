package com.wisecrm.employeeservice.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDto {
    private String id;

    private String firstName;

    private String lastName;

    private String email;

    private String userName;

    private String password;

    private String role;
}

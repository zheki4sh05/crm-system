package com.wisecrm.authorizationservice.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {
    private String id;

    private String firstName;

    private String lastName;

    private String email;

    private String userName;

    private String password;
}

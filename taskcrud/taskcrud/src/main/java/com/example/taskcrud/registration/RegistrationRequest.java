package com.example.taskcrud.registration;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
//@EqualsAndHashCode và @ToString là hai annotation được cung cấp bởi thư viện Lombok để giảm thiểu việc phải viết code boilerplate trong Java.
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
}

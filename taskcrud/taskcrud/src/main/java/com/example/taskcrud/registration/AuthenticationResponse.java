package com.example.taskcrud.registration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//Tạo các getter, setter, constructo
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String token;
    private Boolean exist = false;
    private String noticeIfUserExist;
}

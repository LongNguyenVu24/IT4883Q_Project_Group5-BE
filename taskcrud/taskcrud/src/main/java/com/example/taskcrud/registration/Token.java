package com.example.taskcrud.registration;

import com.example.taskcrud.appuser.AppUser;
import com.example.taskcrud.appuser.TokenType;
import jakarta.persistence.*;
@Entity
public class Token {
    @Id
    @GeneratedValue
    public Integer id;

    @Column(nullable = false)
    public String token;
    public Boolean revoked;
    public Boolean expired;
    @Enumerated(EnumType.STRING)
    public TokenType tokenType = TokenType.BEARER;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    public AppUser user;
//    @JoinColumn(name = "user_id",nullable = false)
////    @ManyToOne(fetch = FetchType.LAZY)
//    @ManyToOne
//    public AppUser appUser;

}

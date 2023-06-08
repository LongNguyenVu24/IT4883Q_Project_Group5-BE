package com.example.taskcrud.registration.token;

import com.example.taskcrud.appuser.AppUser;
import com.example.taskcrud.appuser.TokenType;
import jakarta.persistence.*;

public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false)
    public String token;
    public Boolean revoked;
    public Boolean expired;
    @Enumerated(EnumType.STRING)
    public TokenType tokenType = TokenType.BEARER;

    @JoinColumn(name = "user_id",nullable = false)
//    @ManyToOne(fetch = FetchType.LAZY)
    @OneToOne
    public AppUser appUser;

}

package com.example.taskcrud.registration.token;

import com.example.taskcrud.appuser.AppUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class ConfirmationToken {
    @Id
    @SequenceGenerator(
            name = "confirmation_token_sequence",
            sequenceName = "confirmation_token_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator =  "confirmation_token_sequence"
    )
//    Khi sử dụng GenerationType.AUTO, JPA sẽ tự động chọn strategy phù hợp dựa trên cơ sở dữ liệu mà bạn đang sử dụng.
//    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long id;
    @Column(nullable = false)
    private String token;

//    The corresponding column in the database can not contain nnull value
    @Column(nullable = false)
    private LocalDateTime createAt;
    @Column(nullable = false)

    private LocalDateTime expiredAt;

    private LocalDateTime confirmAt;

    //One user can have many Confirmation token
    @ManyToOne
//    @JoinColumn(name = "app_user_id") chỉ định tên của cột khóa ngoại trong bảng hiện tại.
    @JoinColumn(nullable = false,
    name = "app_user_id"
    )
    private AppUser appUser;

    public ConfirmationToken(String token ,LocalDateTime createAt, LocalDateTime expiredAt, AppUser appUser) {
        this.token=token;
        this.createAt = createAt;
        this.expiredAt = expiredAt;
        this.appUser = appUser;
    }

    public ConfirmationToken() {

    }

}

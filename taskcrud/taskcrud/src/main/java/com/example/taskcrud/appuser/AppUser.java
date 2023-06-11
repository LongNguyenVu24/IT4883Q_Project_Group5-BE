package com.example.taskcrud.appuser;


import com.example.taskcrud.registration.Token;
import jakarta.persistence.*;
import lombok.*;
//import org.hibernate.mapping.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class AppUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String fullName;
    private String email;
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;
    //    Thuộc tính EnumType.STRING được truyền vào Annotation để chỉ định rằng giá trị của enum sẽ được lưu trữ dưới dạng chuỗi (string) trong cơ sở dữ liệu.
    @Enumerated(EnumType.STRING)
    private AppUserRole appUSerRole;






    //    phuwong thức tra về các GrantedAuthority mà người dùng được phân quyền
//    Ví dụ, một GrantedAuthority có thể đại diện cho quyền truy cập vào trang quản trị,
//    một quyền truy cập vào trang xem thông tin cá nhân, hoặc một quyền truy cập vào các tính năng của ứng dụng.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
// tạo ra 1 grantedAuthority tu 1 tham so truền vào
//        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(appUSerRole.name());
        List<GrantedAuthority>grantedAuthorities = List.of(new SimpleGrantedAuthority(appUSerRole.name()));
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //    Phương thức isAccountNonLocked được sử dụng để kiểm tra trạng thái khóa của tài khoản người dùng.
//    Nếu tài khoản người dùng bị khóa, thì người dùng không thể đăng nhập vào hệ thống.
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    //Phương thức này trả về một giá trị boolean xác định xem các thông tin xác thực của tài khoản người dùng có hết hạn hay không.
//
//Nếu các thông tin xác thực của tài khoản người dùng không hết hạn, phương thức trả về giá trị true, ngược lại, nếu các thông tin xác thực của tài khoản người dùng đã hết hạn, phương thức trả về giá trị false.
//
//Thông thường, thông tin xác thực bao gồm mật khẩu của người dùng hoặc các thông tin xác thực khác được sử dụng để xác thực người dùng. Các thông tin xác thực này có thể được yêu cầu để được cập nhật định kỳ để đảm bảo tính bảo mật của hệ thống.
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

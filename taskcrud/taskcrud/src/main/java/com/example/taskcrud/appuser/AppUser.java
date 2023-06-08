package com.example.taskcrud.appuser;


import com.example.taskcrud.registration.token.Token;
import jakarta.persistence.*;
import lombok.*;
//import org.hibernate.mapping.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Data
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class AppUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastName;
    private String email;
    private String password;

    @OneToOne(mappedBy = "user")
    private Token token;
    //    Thuộc tính EnumType.STRING được truyền vào Annotation để chỉ định rằng giá trị của enum sẽ được lưu trữ dưới dạng chuỗi (string) trong cơ sở dữ liệu.
    @Enumerated(EnumType.STRING)
    private AppUSerRole appUSerRole;






    //    phuwong thức tra về các GrantedAuthority mà người dùng được phân quyền
//    Ví dụ, một GrantedAuthority có thể đại diện cho quyền truy cập vào trang quản trị,
//    một quyền truy cập vào trang xem thông tin cá nhân, hoặc một quyền truy cập vào các tính năng của ứng dụng.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
// tạo ra 1 grantedAuthority tu 1 tham so truền vào
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(appUSerRole.name());

//        check xem ng dùng có quyền truy cập vào 1 tài khuyên cụ thể ko
//        Phương thức Collections.singleton() là một phương thức tĩnh được cung cấp bởi lớp Collections trong Java,
//        nó được sử dụng để tạo ra một Set bao gồm duy nhất một phần tử.
//        Trong trường hợp này, phương thức singleton() được sử dụng để tạo ra một Set duy nhất chứa đối tượng authority.
        return Collections.singleton(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastName() {
        return lastName;
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

package com.example.taskcrud.security;

import com.example.taskcrud.appuser.AppUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;


//public class WebSecurityConfig  extends WebSecurityConfigurerAdapter  {
//
//    private final AppUserService appUserService;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(daoAuthenticationProvider());
//    }
//
//    //http.csrf().disable(): Dòng này tắt bảo vệ CSRF cho các yêu cầu HTTP. CSRF (Cross-Site Request Forgery) là một loại tấn công mà một trang web độc hại cố gắng thực thi các hành động thay mặt cho người dùng đã xác thực.
////
////authorizeRequests(): Phương thức này trả về một đối tượng cấu hình cho phép bạn xác định các yêu cầu nào cần được xác thực và cách thức xác thực.
////
////antMatchers("/api/v*/registration/**"): Dòng này chỉ định một mẫu để khớp với các URL nên được cho phép truy cập mà không cần xác thực. Trong trường hợp này, bất kỳ URL nào bắt đầu bằng "/api/v" theo sau bởi bất kỳ số nào và "/registration/" và các phần đường dẫn bổ sung sẽ khớp với mẫu này.
////
////permitAll(): Dòng này cho phép tất cả các yêu cầu khớp với mẫu trên được truy cập mà không cần xác thực.
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable().authorizeRequests().antMatchers("/api/v*/registration/**").permitAll().anyRequest().authenticated().and().formLogin();
//
//
//
//    }
//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider(){
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setPasswordEncoder(bCryptPasswordEncoder);
//        provider.setUserDetailsService(appUserService);
//        return provider;
//    }
//}
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig {
    private final AppUserService appUserService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebSecurityConfig(AppUserService appUserService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.appUserService = appUserService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

//todo: Trong phương thức này, chúng ta tạo ra một đối tượng DaoAuthenticationProvider và
// cấu hình nó bằng cách đặt UserDetailsService và PasswordEncoder. UserDetailsService là một interface
// trong Spring Security được sử dụng để tìm kiếm thông tin người dùng.
// Trong ví dụ này, chúng ta sử dụng appUserService để cung cấp thông tin người dùng. appUserService là một đối tượng đ
// ược định nghĩa ở một nơi khác trong ứng dụng của bạn và triển khai UserDetailsService.
@Bean
public DaoAuthenticationProvider daoAuthenticationProvider(){
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setPasswordEncoder(bCryptPasswordEncoder);
    provider.setUserDetailsService(appUserService);
    return provider;
}
@Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return new ProviderManager(Arrays.asList(daoAuthenticationProvider()));
    }

    //    @Bean
//    public AuthenticationManager authManager( AuthenticationManagerBuilder auth,HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, AppUserService userDetailService)
//            throws Exception {
//        return auth
//                .userDetailsService(userDetailService)
//                .passwordEncoder(bCryptPasswordEncoder)
//                .and()
//                .build();
//    }
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf().disable()
            .authorizeRequests(authorize -> authorize
                    .requestMatchers("/api/v*/registration/**").permitAll()
                    .anyRequest().authenticated()
            )
            .formLogin(withDefaults());

    return http.build();
}
}
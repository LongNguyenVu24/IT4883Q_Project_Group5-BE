package com.example.taskcrud.security;

import com.example.taskcrud.appuser.AppUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig {
    private final AppUserService appUserService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
//    private final SecurityConfig securityConfig;
    public WebSecurityConfig(AppUserService appUserService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.appUserService = appUserService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//        this.securityConfig = securityConfig;
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
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf().disable()
            .authorizeRequests(authorize -> authorize
                    .requestMatchers("**").permitAll()
                    .anyRequest().authenticated()
            )
            .formLogin(withDefaults());

    return http.build();
}

}
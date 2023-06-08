//package com.example.taskcrud.security;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Component
////Todo  tạo một constructor với tất cả các trường dữ liệu final hoặc được đánh dấu bằng @NonNull. Annotation này giúp rút
//// todo ngắn mã và giảm thiểu việc phải viết constructor thủ công.
//@RequiredArgsConstructor
//public class SecurityConfig extends OncePerRequestFilter {
//
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
//        // Get the current user details from the security context
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        if (principal instanceof UserDetails) {
//            UserDetails userDetails = (UserDetails) principal;
//            // Check if the user is enabled
//            if (userDetails.isEnabled()) {
//
//                // Permit all requests
//                chain.doFilter(request, response);
//                return;
//            }
//        }
//
//        // If the user is not enabled, return an error response
//        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied");
//    }
//}
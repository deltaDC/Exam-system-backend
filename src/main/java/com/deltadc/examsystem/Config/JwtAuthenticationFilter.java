package com.deltadc.examsystem.Config;

/*
Lớp này dùng để filter các request của người dùng
Khi người dùng gửi request đến server sẽ chạy qua lớp này đầu tiên
Kiểm tra xem request có chứa header 'authorization' không
 */


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userName;

        // Người dùng chưa được xác thực -> chuyển tới filter khác
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7);

        //lấy username từ claims
        userName = jwtService.extractUsername(jwt);

        // Kiểm tra nếu địa chỉ email của người dùng đã được trích xuất từ token và
        // không có thông tin xác thực nào trong SecurityContextHolder
        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            //lấy ra thông tin người dùng bằng interface UserDatails của Spring
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);

            if (jwtService.isTokenValid(jwt, userDetails)) {
                //tạo đối tượng từ lớp xác thực UsernamePasswordAuthenticationToken của spring security
                //set userDetails và quyền hạn cho nó
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                // thêm thông tin về ip và trình duyệt của user vào authToken
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                //lưu lại người dùng và quyền vào SecurityContextHolder
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);

    }
}

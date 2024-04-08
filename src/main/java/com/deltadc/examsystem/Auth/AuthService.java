package com.deltadc.examsystem.Auth;

import com.deltadc.examsystem.Config.JwtService;
import com.deltadc.examsystem.User.User;
import com.deltadc.examsystem.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public ResponseEntity<AuthResponse> signup(SignUpRequest request) {
        if(userRepository.findByUsername(request.getUsername()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(AuthResponse.builder().message("nguoi dung da ton tai").build());
        }


        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("USER");

        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.OK)
                .body(AuthResponse.builder().message("tao moi user thanh cong").build());
    }

    public ResponseEntity<AuthResponse> authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        var jwtToken = jwtService.generateToken((UserDetails) user);
        return ResponseEntity.status(HttpStatus.OK)
                .body(AuthResponse.builder()
                        .token(jwtToken)
                        .username(user.getUsername())
                        .userId(String.valueOf(user.getUserId()))
                        .role(user.getRole())
                        .build()
                );
    }

    public ResponseEntity<AuthResponse> signupAdmin(SignUpRequest request) {
        if(userRepository.findByUsername(request.getUsername()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(AuthResponse.builder().message("admin da ton tai").build());
        }


        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("ADMIN");

        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.OK)
                .body(AuthResponse.builder().message("tao moi admin thanh cong").build());
    }
}

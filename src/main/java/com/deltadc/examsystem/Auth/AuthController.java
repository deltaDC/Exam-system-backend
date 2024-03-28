package com.deltadc.examsystem.Auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authenticationService;

    //dki user moi
    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(@RequestBody SignUpRequest request) {
        return authenticationService.signup(request);
    }

    //dang nhap
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest request) {
        return authenticationService.authenticate(request);
    }

    //dang ki admin moi
    @PostMapping("/signup-admin")
    public ResponseEntity<AuthResponse> signupAdmin(@RequestBody SignUpRequest request) {
        return authenticationService.signupAdmin(request);
    }

}

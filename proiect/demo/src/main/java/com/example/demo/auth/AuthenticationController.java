package com.example.demo.auth;

//import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;
//    @Operation(tags = {"Registration"})
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }

//    @Operation(tags = {"Authentication"})
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authentication(
            @RequestBody AuthenticationRequest request

    ) {
        return ResponseEntity.ok(service.authenticate(request));

    }
}

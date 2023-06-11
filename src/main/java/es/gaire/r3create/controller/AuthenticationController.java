package es.gaire.r3create.controller;

import es.gaire.r3create.auth.AuthenticationRequest;
import es.gaire.r3create.auth.AuthenticationResponse;
import es.gaire.r3create.auth.RegisterRequest;
import es.gaire.r3create.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        System.out.println("auth" + request);
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}

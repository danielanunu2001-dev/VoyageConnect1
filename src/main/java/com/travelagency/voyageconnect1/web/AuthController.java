package com.travelagency.voyageconnect1.web;

import com.travelagency.voyageconnect1.domain.dto.UserDto;
import com.travelagency.voyageconnect1.security.JwtUtils;
import com.travelagency.voyageconnect1.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtils jwtUtils;

    public AuthController(AuthenticationManager authenticationManager, UserService userService, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        // In a real app, you would return a more complex object with user details and refresh token
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        // In a real app, you would check if username/email already exists

        UserDto userDto = new UserDto(null, signUpRequest.username(), signUpRequest.email(), null);
        userService.createUser(userDto);

        return ResponseEntity.ok("User registered successfully!");
    }

    // DTOs for login and signup requests
    public record LoginRequest(String username, String password) {}
    public record SignupRequest(String username, String email, String password) {}
    public record JwtResponse(String token) {}
}

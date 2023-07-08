package com.realestateproject.controllers;

import com.realestateproject.payload.LoginDto;
import com.realestateproject.security.JwtResponse;
import com.realestateproject.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;
    // http://localhost:8080/api/auth/signin
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new
                        UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // Generate JWT token
        String jwtToken = jwtUtils.generateJwtToken(authentication);

        // Return the JWT token in the response
        return ResponseEntity.ok(new JwtResponse(jwtToken));
        // get token form tokenProvider
        //return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
    }
}
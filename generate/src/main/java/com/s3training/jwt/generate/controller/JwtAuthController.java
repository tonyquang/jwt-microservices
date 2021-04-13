package com.s3training.jwt.generate.controller;

import com.s3training.jwt.generate.configuration.jwt.CustomUserDetails;
import com.s3training.jwt.generate.dto.LoginRequest;
import com.s3training.jwt.generate.dto.LoginResponse;
import com.s3training.jwt.generate.util.JWTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class JwtAuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    Environment environment;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        System.out.println("pass here");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken
                        (request.getUserName(), request.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = JWTokenProvider.generateJWT
                ((CustomUserDetails) authentication.getPrincipal());
        List<GrantedAuthority> roles = (List<GrantedAuthority>) authentication.getAuthorities();
        List<String> rolesName = roles.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        return new ResponseEntity<>(new LoginResponse
                ("true", authentication.getName()+" | port: "+environment.getProperty("local.server.port"), rolesName, token), HttpStatus.OK);
    }
}

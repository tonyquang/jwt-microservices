package com.microservices.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth-server")
public class AuthController {

    @Autowired
    AuthProxy authProxy;

    @PostMapping("/auth/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request){
        ResponseEntity<LoginResponse> res = authProxy.login(request);
        return res;
    }

}

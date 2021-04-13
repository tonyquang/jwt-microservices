package com.microservices.client;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(name = "api-gateway-server")
@RibbonClient(name = "micro-jwt-server")
public interface AuthProxy {

    @PostMapping("micro-jwt-server/auth/login")
    ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request);
}

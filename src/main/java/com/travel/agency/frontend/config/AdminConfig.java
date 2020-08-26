package com.travel.agency.frontend.config;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class AdminConfig {

    @Value("${backend.host.address}")
    private String backendHostAddress;
}

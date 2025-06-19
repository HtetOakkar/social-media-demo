package com.htetoakkar.socialmedia.common.config;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.util.TimeZone;

@Configuration
public class AppConfig {
    // This class can be used to define application-wide beans or configurations.
    // Currently, it is empty but can be extended in the future as needed.

    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }
}

package com.jb.coupon_system.config;

import com.jb.coupon_system.security.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Configuration
public class MapConfig {

    @Bean
    public Map<UUID, Info> map(){
        return new HashMap<>();
    }
}


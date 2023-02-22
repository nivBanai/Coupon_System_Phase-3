package com.jb.coupon_system.jobs;

import com.jb.coupon_system.services.token_service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenExpirationJob {

    private final int FIXED_DELAY = 1000 * 60;

    @Autowired
    private final TokenService tokenService;

    @Scheduled(fixedDelay = FIXED_DELAY)
    public void clearExpiredTokens() {
        tokenService.clearExpiredTokens();
    }
}

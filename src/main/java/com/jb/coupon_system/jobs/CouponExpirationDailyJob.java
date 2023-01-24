package com.jb.coupon_system.jobs;

import com.jb.coupon_system.repos.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CouponExpirationDailyJob {

    private final int INITIAL_DELAY = 2000;
    private final int FIXED_DELAY = 1000 * 60 * 60 * 24;
    @Autowired
    private CouponRepository couponRepository;

    @Scheduled(initialDelay = INITIAL_DELAY, fixedDelay = FIXED_DELAY)
    public void deleteExpiredCoupons() {
//        couponRepository.deleteExpiredCouponsPurchaseHistory();
        couponRepository.deleteExpiredCoupons();
    }
}

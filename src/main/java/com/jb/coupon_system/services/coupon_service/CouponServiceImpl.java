package com.jb.coupon_system.services.coupon_service;

import com.jb.coupon_system.beans.Coupon;
import com.jb.coupon_system.repos.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {

    @Autowired
    private final CouponRepository couponRepository;

    @Override
    public List<Coupon> getAllCoupons() {
        return couponRepository.findAll();
    }

    @Override
    public List<Coupon> get3AlmostOutOfStockCoupons() {
        return couponRepository.getThreeWithLowestAmount();
    }

}

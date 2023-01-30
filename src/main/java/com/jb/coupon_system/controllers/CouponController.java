package com.jb.coupon_system.controllers;

import com.jb.coupon_system.beans.Coupon;
import com.jb.coupon_system.services.coupon_service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/coupon_system")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class CouponController {

    @Autowired
    private CouponService couponService;

    @GetMapping("coupons")
    public List<Coupon> getAllCoupons() {
        return couponService.getAllCoupons();
    }

}

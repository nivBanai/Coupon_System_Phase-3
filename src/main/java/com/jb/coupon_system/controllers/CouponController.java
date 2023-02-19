package com.jb.coupon_system.controllers;

import com.jb.coupon_system.beans.Coupon;
import com.jb.coupon_system.enums.ClientType;
import com.jb.coupon_system.enums.ErrorMsg;
import com.jb.coupon_system.exceptions.CouponSystemException;
import com.jb.coupon_system.services.coupon_service.CouponService;
import com.jb.coupon_system.services.customer_service.CustomerService;
import com.jb.coupon_system.services.token_service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/coupon_system/coupons")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class CouponController {

    @Autowired
    private final CouponService couponService;
    @Autowired
    private final CustomerService customerService;
    @Autowired
    private final TokenService tokenService;

    @PostMapping("purchase/{couponId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Coupon purchaseCoupon(@RequestHeader("Authorization") UUID token, @PathVariable int couponId) throws CouponSystemException {
        if (!tokenService.isTokenValid(token, ClientType.CUSTOMER)) {
            throw new CouponSystemException(ErrorMsg.ACCESS_DENIED);
        }
        return customerService.purchaseCoupon(tokenService.getUserId(token), couponId);
    }

    @GetMapping()
    public List<Coupon> getAllCoupons() {
        return couponService.getAllCoupons();
    }

    @GetMapping("sort/amount/asc")
    public List<Coupon> get3AlmostOutOfStockCoupons() {
        return couponService.get3AlmostOutOfStockCoupons();
    }

}

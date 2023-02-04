package com.jb.coupon_system.controllers;

import com.jb.coupon_system.beans.Coupon;
import com.jb.coupon_system.beans.Customer;
import com.jb.coupon_system.enums.Category;
import com.jb.coupon_system.enums.ClientType;
import com.jb.coupon_system.enums.ErrorMsg;
import com.jb.coupon_system.exceptions.CouponSystemException;
import com.jb.coupon_system.services.customer_service.CustomerService;
import com.jb.coupon_system.services.token_service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/coupon_system/customers")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class CustomerController {

    @Autowired
    private final CustomerService customerService;
    @Autowired
    private final TokenService tokenService;

//    @PostMapping("coupons")
//    @ResponseStatus(HttpStatus.CREATED)
//    public Coupon purchaseCoupon(@RequestHeader("Authorization") UUID token, @RequestBody Coupon coupon) throws CouponSystemException {
//        if (!tokenService.isTokenValid(token, ClientType.CUSTOMER)) {
//            throw new CouponSystemException(ErrorMsg.ACCESS_DENIED);
//        }
//        return customerService.purchaseCoupon(tokenService.getUserId(token), coupon);
//    }

    @GetMapping("coupons")
    public List<Coupon> getCustomerCoupons(@RequestHeader("Authorization") UUID token) throws CouponSystemException {
        if (!tokenService.isTokenValid(token, ClientType.CUSTOMER)) {
            throw new CouponSystemException(ErrorMsg.ACCESS_DENIED);
        }
        return customerService.getCustomerCoupons(tokenService.getUserId(token));
    }

    @GetMapping("coupons/filter/categories/{category}")
    public List<Coupon> getCustomerCouponsByCategory(@RequestHeader("Authorization") UUID token, @PathVariable Category category) throws CouponSystemException {
        if (!tokenService.isTokenValid(token, ClientType.CUSTOMER)) {
            throw new CouponSystemException(ErrorMsg.ACCESS_DENIED);
        }
        return customerService.getCustomerCouponsByCategory(tokenService.getUserId(token), category);
    }

    @GetMapping("coupons/filter/price/max-price")
    public List<Coupon> getCustomerCouponsByPrice(@RequestHeader("Authorization") UUID token, @RequestParam double price) throws CouponSystemException {
        if (!tokenService.isTokenValid(token, ClientType.CUSTOMER)) {
            throw new CouponSystemException(ErrorMsg.ACCESS_DENIED);
        }
        return customerService.getCustomerCouponsByPrice(tokenService.getUserId(token), price);
    }

    @GetMapping("details")
    public Customer getCustomerDetails(@RequestHeader("Authorization") UUID token) throws CouponSystemException {
        if (!tokenService.isTokenValid(token, ClientType.CUSTOMER)) {
            throw new CouponSystemException(ErrorMsg.ACCESS_DENIED);
        }
        return customerService.getCustomerDetails(tokenService.getUserId(token));
    }
}

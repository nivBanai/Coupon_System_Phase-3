package com.jb.coupon_system.controllers;

import com.jb.coupon_system.beans.Company;
import com.jb.coupon_system.beans.Coupon;
import com.jb.coupon_system.dto.CouponPayload;
import com.jb.coupon_system.enums.Category;
import com.jb.coupon_system.enums.ClientType;
import com.jb.coupon_system.enums.ErrorMsg;
import com.jb.coupon_system.exceptions.CouponSystemException;
import com.jb.coupon_system.services.ClientService;
import com.jb.coupon_system.services.company_service.CompanyService;
import com.jb.coupon_system.services.token_servic.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/coupon_system/companies")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class CompanyController {

    @Autowired
    private final CompanyService companyService;
    @Autowired
    private final TokenService tokenService;

    @PostMapping("coupons")
    @ResponseStatus(HttpStatus.CREATED)
    public Coupon addCoupon(@RequestHeader("Authorization") UUID token, @RequestBody CouponPayload couponPayload) throws CouponSystemException {
        if (!tokenService.isTokenValid(token, ClientType.COMPANY)) {
            throw new CouponSystemException(ErrorMsg.ACCESS_DENIED);
        }
        return companyService.addCoupon(tokenService.getUserId(token), couponPayload);
    }

    @PutMapping("coupons/{couponId}")
    public Coupon updateCoupon(@RequestHeader("Authorization") UUID token, @PathVariable int couponId, @RequestBody CouponPayload couponPayload) throws CouponSystemException {
        if (!tokenService.isTokenValid(token, ClientType.COMPANY)) {
            throw new CouponSystemException(ErrorMsg.ACCESS_DENIED);
        }
        return companyService.updateCoupon(tokenService.getUserId(token), couponId, couponPayload);
    }

    @DeleteMapping("coupons/{couponId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCoupon(@RequestHeader("Authorization") UUID token, @PathVariable int couponId) throws CouponSystemException {
        if (!tokenService.isTokenValid(token, ClientType.COMPANY)) {
            throw new CouponSystemException(ErrorMsg.ACCESS_DENIED);
        }
        companyService.deleteCoupon(tokenService.getUserId(token), couponId);
    }

    @GetMapping("coupons")
    public List<Coupon> getCompanyCoupons(@RequestHeader("Authorization") UUID token) throws CouponSystemException {
        if (!tokenService.isTokenValid(token, ClientType.COMPANY)) {
            throw new CouponSystemException(ErrorMsg.ACCESS_DENIED);
        }
        return companyService.getCompanyCoupons(tokenService.getUserId(token));
    }

    @GetMapping("coupons/filter/categories/{category}")
    public List<Coupon> getCompanyCouponsByCategory(@RequestHeader("Authorization") UUID token, @PathVariable Category category) throws CouponSystemException {
        if (!tokenService.isTokenValid(token, ClientType.COMPANY)) {
            throw new CouponSystemException(ErrorMsg.ACCESS_DENIED);
        }
        return companyService.getCompanyCouponsByCategory(tokenService.getUserId(token), category);
    }

    @GetMapping("coupons/filter/price/max-price")
    public List<Coupon> getCompanyCouponsByPrice(@RequestHeader("Authorization") UUID token, @RequestParam double price) throws CouponSystemException {
        if (!tokenService.isTokenValid(token, ClientType.COMPANY)) {
            throw new CouponSystemException(ErrorMsg.ACCESS_DENIED);
        }
        return companyService.getCompanyCouponsByPrice(tokenService.getUserId(token), price);
    }

    @GetMapping("details")
    public Company getCompanyDetails(@RequestHeader("Authorization") UUID token) throws CouponSystemException {
        if (!tokenService.isTokenValid(token, ClientType.COMPANY)) {
            throw new CouponSystemException(ErrorMsg.ACCESS_DENIED);
        }
        return companyService.getCompanyDetails(tokenService.getUserId(token));
    }
}

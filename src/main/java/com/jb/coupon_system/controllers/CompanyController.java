package com.jb.coupon_system.controllers;

import com.jb.coupon_system.beans.Company;
import com.jb.coupon_system.beans.Coupon;
import com.jb.coupon_system.enums.Category;
import com.jb.coupon_system.exceptions.CouponSystemException;
import com.jb.coupon_system.services.ClientService;
import com.jb.coupon_system.services.company_service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/coupon_system/companies")
@CrossOrigin(origins = "*")
public class CompanyController{

    @Autowired
    private CompanyService companyService;

    @PostMapping("{companyId}/coupons")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCoupon(@PathVariable int companyId, @RequestBody Coupon coupon) throws CouponSystemException {
        companyService.addCoupon(companyId, coupon);
    }

    @PutMapping("{companyId}/coupons/{couponId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCoupon(@PathVariable int companyId, @PathVariable int couponId, @RequestBody Coupon coupon) throws CouponSystemException {
        companyService.updateCoupon(companyId, couponId, coupon);
    }

    @DeleteMapping("{companyId}/coupons/{couponId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCoupon(@PathVariable int companyId, @PathVariable int couponId) {
        companyService.deleteCoupon(companyId, couponId);
    }

    @GetMapping("{companyId}/coupons")
    public List<Coupon> getCompanyCoupons(@PathVariable int companyId) {
        return companyService.getCompanyCoupons(companyId);
    }

    @GetMapping("{companyId}/coupons/filter/categories/{category}")
    public List<Coupon> getCompanyCouponsByCategory(@PathVariable int companyId, @PathVariable Category category) {
        return companyService.getCompanyCouponsByCategory(companyId, category);
    }

    @GetMapping("{companyId}/coupons/filter/price/max-price")
    public List<Coupon> getCompanyCouponsByPrice(@PathVariable int companyId, @RequestParam double price) {
        return companyService.getCompanyCouponsByPrice(companyId, price);
    }

    @GetMapping("{companyId}/details")
    public Company getCompanyDetails(@PathVariable int companyId) throws CouponSystemException {
        return companyService.getCompanyDetails(companyId);
    }
}

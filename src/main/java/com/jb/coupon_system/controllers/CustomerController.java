package com.jb.coupon_system.controllers;

import com.jb.coupon_system.beans.Coupon;
import com.jb.coupon_system.beans.Customer;
import com.jb.coupon_system.enums.Category;
import com.jb.coupon_system.exceptions.CouponSystemException;
import com.jb.coupon_system.services.ClientService;
import com.jb.coupon_system.services.customer_service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/coupon_system/customers")
@CrossOrigin(origins = "*")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("{customerId}/coupons")
    @ResponseStatus(HttpStatus.CREATED)
    public void purchaseCoupon(@PathVariable int customerId, @RequestBody Coupon coupon) throws CouponSystemException {
        customerService.purchaseCoupon(customerId, coupon);
    }

    @GetMapping("{customerId}/coupons")
    public List<Coupon> getCustomerCoupons(@PathVariable int customerId) {
        return customerService.getCustomerCoupons(customerId);
    }

    @GetMapping("{customerId}/coupons/filter/categories/{category}")
    public List<Coupon> getCustomerCouponsByCategory(@PathVariable int customerId, @PathVariable Category category) {
        return customerService.getCustomerCouponsByCategory(customerId, category);
    }

    @GetMapping("{customerId}/coupons/filter/price/max-price")
    public List<Coupon> getCustomerCouponsByPrice(@PathVariable int customerId, @RequestParam double price) {
        return customerService.getCustomerCouponsByPrice(customerId, price);
    }

    @GetMapping("{customerId}/details")
    public Customer getCustomerDetails(@PathVariable int customerId) throws CouponSystemException {
        return customerService.getCustomerDetails(customerId);
    }
}

package com.jb.coupon_system.services.customer_service;

import com.jb.coupon_system.beans.Coupon;
import com.jb.coupon_system.beans.Customer;
import com.jb.coupon_system.dto.CustomerPayload;
import com.jb.coupon_system.enums.Category;
import com.jb.coupon_system.exceptions.CouponSystemException;

import java.util.List;

public interface CustomerService {

    Coupon purchaseCoupon(int customerId, int couponId) throws CouponSystemException;

    List<Coupon> getCustomerCoupons(int customerId);

    List<Coupon> getCustomerCouponsByCategory(int customerId, Category category);

    List<Coupon> getCustomerCouponsByPrice(int customerId, double maxPrice);

    Customer getCustomerDetails(int customerId) throws CouponSystemException;
}

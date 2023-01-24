package com.jb.coupon_system.services.company_service;

import com.jb.coupon_system.beans.Company;
import com.jb.coupon_system.beans.Coupon;
import com.jb.coupon_system.enums.Category;
import com.jb.coupon_system.exceptions.CouponSystemException;

import java.util.List;

public interface CompanyService {

    void addCoupon(int companyId, Coupon coupon) throws CouponSystemException;

    void updateCoupon(int companyId, int couponId, Coupon coupon) throws CouponSystemException;

    void deleteCoupon(int companyId, int couponId);

    List<Coupon> getCompanyCoupons(int companyId);

    List<Coupon> getCompanyCouponsByCategory(int companyId, Category category);

    List<Coupon> getCompanyCouponsByPrice(int companyId, double maxPrice);

    Company getCompanyDetails(int companyId) throws CouponSystemException;
}

package com.jb.coupon_system.services.customer_service;

import com.jb.coupon_system.beans.Coupon;
import com.jb.coupon_system.beans.Customer;
import com.jb.coupon_system.dto.CustomerPayload;
import com.jb.coupon_system.enums.Category;
import com.jb.coupon_system.enums.ErrorMsg;
import com.jb.coupon_system.exceptions.CouponSystemException;
import com.jb.coupon_system.services.ClientService;
import com.jb.coupon_system.utils.ServiceUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl extends ClientService implements CustomerService {

    @Override
    public boolean login(String email, String password) {
        return this.customerRepository.existsByEmailAndPassword(email, password);
    }

    @Override
    public int getClientId(String email) throws CouponSystemException {
        return customerRepository.findIdByEmail(email);
    }

    @Override
    public String getClientName(String email) throws CouponSystemException {
        return customerRepository.findNameByEmail(email);
    }

    @Override
    public String getClientProfilePic(String email) throws CouponSystemException {
        return customerRepository.findProfilePicByEmail(email);
    }

    @Override
    public Coupon purchaseCoupon(int customerId, int couponId) throws CouponSystemException {
        Coupon coupon = this.couponRepository.findById(couponId)
                .orElseThrow(() -> new CouponSystemException(ErrorMsg.COUPON_NOT_FOUND));
        if (this.couponRepository.isPurchaseExists(customerId, couponId)) {
            throw new CouponSystemException(ErrorMsg.COUPON_PURCHASE_LIMIT);
        }
        if (ServiceUtils.isCouponOutOfStock(coupon.getAmount())) {
            throw new CouponSystemException(ErrorMsg.OUT_OF_STOCK);
        }
        if (ServiceUtils.isCouponExpired(coupon.getEndDate())) {
            throw new CouponSystemException(ErrorMsg.COUPON_EXPIRED);
        }
        ServiceUtils.reduceCouponAmount(coupon);
//        coupon.setCompany(originalCoupon.getCompany());
        this.couponRepository.saveAndFlush(coupon);
        this.customerRepository.purchaseCoupon(customerId, couponId);
        return coupon;
    }

    @Override
    public List<Coupon> getCustomerCoupons(int customerId) {
        return couponRepository.findByCustomerId(customerId);
    }

    @Override
    public List<Coupon> getCustomerCouponsByCategory(int customerId, Category category) {
        return couponRepository.findByCustomerIdAndCategory(customerId, category.name());
    }

    @Override
    public List<Coupon> getCustomerCouponsByPrice(int customerId, double maxPrice) {
        return couponRepository.findByCustomerIdAndPrice(customerId, maxPrice);
    }

    @Override
    public Customer getCustomerDetails(int customerId) throws CouponSystemException {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new CouponSystemException(ErrorMsg.CUSTOMER_NOT_FOUND));
    }
}

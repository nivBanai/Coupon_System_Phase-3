package com.jb.coupon_system.services;

import com.jb.coupon_system.exceptions.CouponSystemException;
import com.jb.coupon_system.repos.CompanyRepository;
import com.jb.coupon_system.repos.CouponRepository;
import com.jb.coupon_system.repos.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ClientService {

    @Autowired
    protected CompanyRepository companyRepository;
    @Autowired
    protected CustomerRepository customerRepository;
    @Autowired
    protected CouponRepository couponRepository;


    public abstract boolean login(String email, String password) throws CouponSystemException;

    public abstract int getClientId(String email) throws CouponSystemException;
    public abstract String getClientName(String email) throws CouponSystemException;
    public abstract String getClientProfilePic(String email) throws CouponSystemException;
}

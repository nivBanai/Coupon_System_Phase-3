package com.jb.coupon_system.services.admin_service;

import com.jb.coupon_system.beans.Company;
import com.jb.coupon_system.beans.Customer;
import com.jb.coupon_system.dto.CompanyPayload;
import com.jb.coupon_system.dto.CustomerPayload;
import com.jb.coupon_system.exceptions.CouponSystemException;

import java.util.List;

public interface AdminService {

    Company addCompany(CompanyPayload companyPayload) throws CouponSystemException;

    Company updateCompany(int companyId, CompanyPayload companyPayload) throws CouponSystemException;

    void deleteCompany(int companyId);

    List<Company> getAllCompanies();

    Company getSingleCompany(int companyId) throws CouponSystemException;

    Customer addCustomer(CustomerPayload customerPayload) throws CouponSystemException;

    Customer updateCustomer(int customerId, CustomerPayload customerPayload) throws CouponSystemException;

    void deleteCustomer(int customerId);

    List<Customer> getAllCustomers();

    Customer getSingleCustomer(int customerId) throws CouponSystemException;
}

package com.jb.coupon_system.services.admin_service;

import com.jb.coupon_system.beans.Company;
import com.jb.coupon_system.beans.Customer;
import com.jb.coupon_system.enums.ErrorMsg;
import com.jb.coupon_system.exceptions.CouponSystemException;
import com.jb.coupon_system.services.ClientService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AdminServiceImpl extends ClientService implements AdminService {

    @Value("${admin.email}")
    private String adminEmail;
    @Value("${admin.password}")
    private String adminPassword;

    @Override
    public boolean login(String email, String password) {
        return Objects.equals(email, adminEmail) && Objects.equals(password, adminPassword);
    }

    @Override
    public int getId(String email, String password) throws CouponSystemException {
        return -1;
    }

    @Override
    public void addCompany(Company company) throws CouponSystemException {
        if (this.companyRepository.existsByEmail(company.getEmail())) {
            throw new CouponSystemException(ErrorMsg.EMAIL_TAKEN);
        }
        if (this.companyRepository.existsByName(company.getName())) {
            throw new CouponSystemException(ErrorMsg.NAME_TAKEN);
        }
        this.companyRepository.save(company);
    }

    @Override
    public void updateCompany(int companyId, Company company) throws CouponSystemException {
        Company originalCompany = this.companyRepository.findById(companyId)
                .orElseThrow(() -> new CouponSystemException(ErrorMsg.COMPANY_NOT_FOUND));
        if (companyId != company.getId()) {
            throw new CouponSystemException(ErrorMsg.COMPANY_ID_ERROR);
        }
        if (!Objects.equals(originalCompany.getName(), company.getName())) {
            throw new CouponSystemException(ErrorMsg.COMPANY_NAME_ERROR);
        }
        company.setId(companyId);
        this.companyRepository.saveAndFlush(company);
    }

    @Override
    public void deleteCompany(int companyId) {
//        couponRepository.deletePurchaseHistoryByCompanyId(companyId);
        companyRepository.deleteById(companyId);
    }

    @Override
    public List<Company> getAllCompanies() {
        return this.companyRepository.findAll();
    }

    @Override
    public Company getSingleCompany(int companyId) throws CouponSystemException {
        return this.companyRepository.findById(companyId)
                .orElseThrow(() -> new CouponSystemException(ErrorMsg.COMPANY_NOT_FOUND));
    }

    @Override
    public void addCustomer(Customer customer) throws CouponSystemException {
        if (this.customerRepository.existsByEmail(customer.getEmail())) {
            throw new CouponSystemException(ErrorMsg.EMAIL_TAKEN);
        }
        this.customerRepository.save(customer);
    }

    @Override
    public void updateCustomer(int customerId, Customer customer) throws CouponSystemException {
        if (customerId != customer.getId()) {
            throw new CouponSystemException(ErrorMsg.CUSTOMER_ID_ERROR);
        }
        customer.setId(customerId);
        this.customerRepository.saveAndFlush(customer);
    }

    @Override
    public void deleteCustomer(int customerId) {
//        this.couponRepository.deletePurchaseHistoryByCustomerId(customerId);
        this.customerRepository.deleteById(customerId);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getSingleCustomer(int customerId) throws CouponSystemException {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new CouponSystemException(ErrorMsg.CUSTOMER_NOT_FOUND));
    }
}

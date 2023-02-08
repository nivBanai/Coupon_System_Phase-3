package com.jb.coupon_system.services.admin_service;

import com.jb.coupon_system.beans.Company;
import com.jb.coupon_system.beans.Customer;
import com.jb.coupon_system.dto.CompanyPayload;
import com.jb.coupon_system.dto.CustomerPayload;
import com.jb.coupon_system.enums.ErrorMsg;
import com.jb.coupon_system.exceptions.CouponSystemException;
import com.jb.coupon_system.services.ClientService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class AdminServiceImpl extends ClientService implements AdminService {

    @Value("${admin.email}")
    private String adminEmail;
    @Value("${admin.password}")
    private String adminPassword;

    @Override
    public boolean login(String email, String password) throws CouponSystemException {
        return (Objects.equals(email, adminEmail) && Objects.equals(password, adminPassword));
    }

    @Override
    public int getClientId(String email) {
        return -1;
    }

    @Override
    public String getClientName(String email) throws CouponSystemException {
        return "Admin";
    }

    @Override
    public String getClientProfilePic(String email) throws CouponSystemException {
        return "";
    }

    @Override
    public Company addCompany(CompanyPayload companyPayload) throws CouponSystemException {
        if (this.companyRepository.existsByEmail(companyPayload.getEmail())) {
            throw new CouponSystemException(ErrorMsg.EMAIL_TAKEN);
        }
        if (this.companyRepository.existsByName(companyPayload.getName())) {
            throw new CouponSystemException(ErrorMsg.NAME_TAKEN);
        }

        return this.companyRepository.save(Company.builder()
                .name(companyPayload.getName())
                .email(companyPayload.getEmail())
                .password(companyPayload.getPassword())
                .profilePic(companyPayload.getProfilePic())
                .coupons(new ArrayList<>())
                .build());
    }

    @Override
    public Company updateCompany(int companyId, CompanyPayload companyPayload) throws CouponSystemException {
        Company originalCompany = this.companyRepository.findById(companyId)
                .orElseThrow(() -> new CouponSystemException(ErrorMsg.COMPANY_NOT_FOUND));

        if (!Objects.equals(originalCompany.getName(), companyPayload.getName())) {
            throw new CouponSystemException(ErrorMsg.COMPANY_NAME_ERROR);
        }

        originalCompany.setName(companyPayload.getName());
        originalCompany.setEmail(companyPayload.getEmail());
        originalCompany.setPassword(companyPayload.getPassword());
        originalCompany.setCoupons(this.couponRepository.findByCompanyId(companyId));
        return this.companyRepository.saveAndFlush(originalCompany);
    }

    @Override
    public void deleteCompany(int companyId) {
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
    public Customer addCustomer(CustomerPayload customerPayload) throws CouponSystemException {
        if (this.customerRepository.existsByEmail(customerPayload.getEmail())) {
            throw new CouponSystemException(ErrorMsg.EMAIL_TAKEN);
        }
        return this.customerRepository.save(Customer.builder()
                .firstName(customerPayload.getFirstName())
                .lastName(customerPayload.getLastName())
                .email(customerPayload.getEmail())
                .password(customerPayload.getPassword())
                .profilePic(customerPayload.getProfilePic())
                .coupons(new ArrayList<>())
                .build());
    }

    @Override
    public Customer updateCustomer(int customerId, CustomerPayload customerPayload) throws CouponSystemException {

        Customer originalCustomer = this.customerRepository.findById(customerId)
                .orElseThrow(() -> new CouponSystemException(ErrorMsg.CUSTOMER_NOT_FOUND));

        originalCustomer.setFirstName(customerPayload.getFirstName());
        originalCustomer.setLastName(customerPayload.getLastName());
        originalCustomer.setEmail(customerPayload.getEmail());
        originalCustomer.setPassword(customerPayload.getPassword());
        originalCustomer.setCoupons(this.couponRepository.findByCustomerId(customerId));

        return this.customerRepository.saveAndFlush(originalCustomer);
    }

    @Override
    public void deleteCustomer(int customerId) {
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

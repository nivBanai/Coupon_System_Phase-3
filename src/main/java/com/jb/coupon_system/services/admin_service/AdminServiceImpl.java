package com.jb.coupon_system.services.admin_service;

import com.jb.coupon_system.beans.Company;
import com.jb.coupon_system.beans.Customer;
import com.jb.coupon_system.dto.ClientInfoDto;
import com.jb.coupon_system.dto.CompanyPayload;
import com.jb.coupon_system.dto.CustomerPayload;
import com.jb.coupon_system.enums.ErrorMsg;
import com.jb.coupon_system.exceptions.CouponSystemException;
import com.jb.coupon_system.services.ClientService;
import org.springframework.beans.BeanUtils;
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
    public boolean login(String email, String password) {
        return (Objects.equals(email, adminEmail) && Objects.equals(password, adminPassword));
    }

    @Override
    public ClientInfoDto getClientIdAndNameAndProfilePic(String email) {
        return ClientInfoDto.builder()
                .id(-1)
                .name("Admin")
                .build();
    }

    @Override
    public Company addCompany(CompanyPayload companyPayload) throws CouponSystemException {

        if (this.companyRepository.existsByEmail(companyPayload.getEmail())) {
            throw new CouponSystemException(ErrorMsg.EMAIL_TAKEN);
        }
        if (this.companyRepository.existsByName(companyPayload.getName())) {
            throw new CouponSystemException(ErrorMsg.NAME_TAKEN);
        }

        return this.companyRepository.save(
                Company.builder()
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

        BeanUtils.copyProperties(companyPayload, originalCompany);

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

        return this.customerRepository.save(
                Customer.builder()
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

        BeanUtils.copyProperties(customerPayload, originalCustomer);

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

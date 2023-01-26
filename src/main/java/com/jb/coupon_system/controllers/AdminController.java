package com.jb.coupon_system.controllers;

import com.jb.coupon_system.beans.Company;
import com.jb.coupon_system.beans.Customer;
import com.jb.coupon_system.dto.CompanyPayload;
import com.jb.coupon_system.dto.CustomerPayload;
import com.jb.coupon_system.enums.ClientType;
import com.jb.coupon_system.enums.ErrorMsg;
import com.jb.coupon_system.exceptions.CouponSystemException;
import com.jb.coupon_system.services.admin_service.AdminService;
import com.jb.coupon_system.services.token_servic.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/coupon_system")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AdminController {

    @Autowired
    private final AdminService adminService;
    @Autowired
    private final TokenService tokenService;

    @PostMapping("companies")
    @ResponseStatus(HttpStatus.CREATED)
    public Company addCompany(@RequestHeader("Authorization") UUID token, @RequestBody CompanyPayload companyPayload) throws CouponSystemException {
        if (!tokenService.isTokenValid(token, ClientType.ADMINISTRATOR)) {
            throw new CouponSystemException(ErrorMsg.ACCESS_DENIED);
        }
        return adminService.addCompany(companyPayload);
    }

    @PutMapping("companies/{companyId}")
    public Company updateCompany(@RequestHeader("Authorization") UUID token, @PathVariable int companyId, @RequestBody CompanyPayload companyPayload) throws CouponSystemException {
        if (!tokenService.isTokenValid(token, ClientType.ADMINISTRATOR)) {
            throw new CouponSystemException(ErrorMsg.ACCESS_DENIED);
        }
        return adminService.updateCompany(companyId, companyPayload);
    }

    @DeleteMapping("companies/{companyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompany(@RequestHeader("Authorization") UUID token, @PathVariable int companyId) throws CouponSystemException {
        if (!tokenService.isTokenValid(token, ClientType.ADMINISTRATOR)) {
            throw new CouponSystemException(ErrorMsg.ACCESS_DENIED);
        }
        adminService.deleteCompany(companyId);
    }

    @GetMapping("companies")
    public List<Company> getAllCompanies(@RequestHeader("Authorization") UUID token) throws CouponSystemException {
        if (!tokenService.isTokenValid(token, ClientType.ADMINISTRATOR)) {
            throw new CouponSystemException(ErrorMsg.ACCESS_DENIED);
        }
        return adminService.getAllCompanies();
    }

    @GetMapping("companies/{companyId}")
    public Company getSingleCompany(@RequestHeader("Authorization") UUID token, @PathVariable int companyId) throws CouponSystemException {
        if (!tokenService.isTokenValid(token, ClientType.ADMINISTRATOR)) {
            throw new CouponSystemException(ErrorMsg.ACCESS_DENIED);
        }
        return adminService.getSingleCompany(companyId);
    }

    @PostMapping("customers")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer addCustomer(@RequestHeader("Authorization") UUID token, @RequestBody CustomerPayload customerPayload) throws CouponSystemException {
        if (!tokenService.isTokenValid(token, ClientType.ADMINISTRATOR)) {
            throw new CouponSystemException(ErrorMsg.ACCESS_DENIED);
        }
        return adminService.addCustomer(customerPayload);
    }

    @PutMapping("customers/{customerId}")
    public Customer updateCustomer(@RequestHeader("Authorization") UUID token, @PathVariable int customerId, @RequestBody CustomerPayload customerPayload) throws CouponSystemException {
        if (!tokenService.isTokenValid(token, ClientType.ADMINISTRATOR)) {
            throw new CouponSystemException(ErrorMsg.ACCESS_DENIED);
        }
        return adminService.updateCustomer(customerId, customerPayload);
    }

    @DeleteMapping("customers/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@RequestHeader("Authorization") UUID token, @PathVariable int customerId) throws CouponSystemException {
        if (!tokenService.isTokenValid(token, ClientType.ADMINISTRATOR)) {
            throw new CouponSystemException(ErrorMsg.ACCESS_DENIED);
        }
        adminService.deleteCustomer(customerId);
    }

    @GetMapping("customers")
    public List<Customer> getAllCustomers(@RequestHeader("Authorization") UUID token) throws CouponSystemException {
        if (!tokenService.isTokenValid(token, ClientType.ADMINISTRATOR)) {
            throw new CouponSystemException(ErrorMsg.ACCESS_DENIED);
        }
        return adminService.getAllCustomers();
    }

    @GetMapping("customers/{customerId}")
    public Customer getSingleCustomer(@RequestHeader("Authorization") UUID token, @PathVariable int customerId) throws CouponSystemException {
        if (!tokenService.isTokenValid(token, ClientType.ADMINISTRATOR)) {
            throw new CouponSystemException(ErrorMsg.ACCESS_DENIED);
        }
        return adminService.getSingleCustomer(customerId);
    }
}

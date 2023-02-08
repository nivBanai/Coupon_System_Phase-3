package com.jb.coupon_system.controllers;

import com.jb.coupon_system.beans.Customer;
import com.jb.coupon_system.dto.CustomerPayload;
import com.jb.coupon_system.dto.LoginReqDto;
import com.jb.coupon_system.dto.LoginResDto;
import com.jb.coupon_system.exceptions.CouponSystemException;
import com.jb.coupon_system.login.LoginManager;
import com.jb.coupon_system.services.admin_service.AdminService;
import com.jb.coupon_system.services.token_service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/coupon_system")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private final LoginManager loginManager;

    @Autowired
    private final TokenService tokenService;

    @Autowired
    private final AdminService adminService;

    @PostMapping("login")
    @ResponseStatus(HttpStatus.CREATED)
    public LoginResDto login(@RequestBody LoginReqDto loginReqDto) throws CouponSystemException {
        return loginManager.login(loginReqDto);
    }

    @DeleteMapping("logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(@RequestHeader("Authorization") UUID token) {
        tokenService.deleteToken(token);
    }

    @PostMapping("register")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer register(@RequestBody CustomerPayload customerPayload) throws CouponSystemException {
        return adminService.addCustomer(customerPayload);
    }
}

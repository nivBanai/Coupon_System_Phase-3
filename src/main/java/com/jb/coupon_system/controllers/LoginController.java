package com.jb.coupon_system.controllers;

import com.jb.coupon_system.dto.LoginReqDto;
import com.jb.coupon_system.dto.LoginResDto;
import com.jb.coupon_system.exceptions.CouponSystemException;
import com.jb.coupon_system.login.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/coupon_system")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginManager;

    @PostMapping("login")
    @ResponseStatus(HttpStatus.CREATED)
    public LoginResDto login(@RequestBody LoginReqDto loginReqDto) throws CouponSystemException {
        return loginManager.login(loginReqDto);
    }
}

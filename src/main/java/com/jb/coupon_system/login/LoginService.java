package com.jb.coupon_system.login;

import com.jb.coupon_system.dto.LoginReqDto;
import com.jb.coupon_system.dto.LoginResDto;
import com.jb.coupon_system.enums.ClientType;
import com.jb.coupon_system.enums.ErrorMsg;
import com.jb.coupon_system.exceptions.CouponSystemException;
import com.jb.coupon_system.services.ClientService;
import com.jb.coupon_system.services.token_servic.TokenService;
import com.jb.coupon_system.utils.PrintUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private ClientService adminServiceImpl;
    @Autowired
    private ClientService companyServiceImpl;
    @Autowired
    private ClientService customerServiceImpl;
    @Autowired
    private TokenService tokenService;

    public LoginResDto login(LoginReqDto loginReqDto) throws CouponSystemException {
        String email = loginReqDto.getEmail();
        String password = loginReqDto.getPassword();
        ClientType clientType = loginReqDto.getClientType();
        ClientService clientService;

        switch (clientType) {
            case ADMINISTRATOR:
                if (adminServiceImpl.login(email, password)) {
                    PrintUtils.printSuccess("Admin logged-in successfully!");
                    clientService = adminServiceImpl;
                    break;
                }
            case COMPANY:
                if (companyServiceImpl.login(email, password)) {
                    PrintUtils.printSuccess("Company logged-in successfully!");
                    clientService = companyServiceImpl;
                    break;
                }
            case CUSTOMER:
                if (customerServiceImpl.login(email, password)) {
                    PrintUtils.printSuccess("Customer logged-in successfully!");
                    clientService = customerServiceImpl;
                    break;
                }
            default:
                throw new CouponSystemException(ErrorMsg.LOGIN_ERROR);
        }

        return LoginResDto.builder()
                .token(tokenService.createToken(clientService, clientType, clientService.getId(email)))
                .build();
    }
}


package com.jb.coupon_system.login;

import com.jb.coupon_system.dto.ClientInfoDto;
import com.jb.coupon_system.dto.LoginReqDto;
import com.jb.coupon_system.dto.LoginResDto;
import com.jb.coupon_system.enums.ClientType;
import com.jb.coupon_system.enums.ErrorMsg;
import com.jb.coupon_system.exceptions.CouponSystemException;
import com.jb.coupon_system.services.ClientService;
import com.jb.coupon_system.services.token_service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginManager {

    @Autowired
    private final ClientService adminServiceImpl;
    @Autowired
    private final ClientService companyServiceImpl;
    @Autowired
    private final ClientService customerServiceImpl;
    @Autowired
    private final TokenService tokenService;

    private void validateLogin(boolean isLoginSuccessful) throws CouponSystemException {
        if (!isLoginSuccessful) {
            throw new CouponSystemException(ErrorMsg.LOGIN_ERROR);
        }
    }

    public LoginResDto login(LoginReqDto loginReqDto) throws CouponSystemException {

        String email = loginReqDto.getEmail();
        String password = loginReqDto.getPassword();
        ClientType clientType = loginReqDto.getClientType();
        ClientService clientService;

        switch (clientType) {

            case ADMINISTRATOR:
                validateLogin(adminServiceImpl.login(email, password));
                clientService = adminServiceImpl;
                break;

            case COMPANY:
                validateLogin(companyServiceImpl.login(email, password));
                clientService = companyServiceImpl;
                break;

            case CUSTOMER:
                validateLogin(customerServiceImpl.login(email, password));
                clientService = customerServiceImpl;
                break;

            default:
                throw new CouponSystemException(ErrorMsg.INVALID_CLIENT_TYPE);
        }

        ClientInfoDto clientInfo = clientService.getClientIdAndNameAndProfilePic(email);

        return LoginResDto.builder()
                .token(tokenService.createToken(clientService, clientType, clientInfo.getId()))
                .name(clientInfo.getName())
                .profilePic(clientInfo.getProfilePic())
                .build();
    }
}


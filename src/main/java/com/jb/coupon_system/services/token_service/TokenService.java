package com.jb.coupon_system.services.token_service;

import com.jb.coupon_system.enums.ClientType;
import com.jb.coupon_system.exceptions.CouponSystemException;
import com.jb.coupon_system.services.ClientService;

import java.util.UUID;

public interface TokenService {

    UUID createToken(ClientService client, ClientType clientType, int clientId);

    void deleteToken(UUID token);

    void clearTokens();

    boolean isTokenValid(UUID token, ClientType clientType) throws CouponSystemException;

    int getUserId(UUID token) throws CouponSystemException;
}

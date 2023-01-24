package com.jb.coupon_system.services.token_servic;

import com.jb.coupon_system.enums.ClientType;
import com.jb.coupon_system.services.ClientService;

import java.util.UUID;

public interface TokenService {

    UUID createToken(ClientService client, ClientType clientType, int clientId);

    void clearTokens();

    boolean isValid(UUID token, ClientType clientType);

    int getUserId(UUID token);
}

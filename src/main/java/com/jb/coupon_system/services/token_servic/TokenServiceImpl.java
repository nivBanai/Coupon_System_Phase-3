package com.jb.coupon_system.services.token_servic;

import com.jb.coupon_system.beans.Company;
import com.jb.coupon_system.beans.Customer;
import com.jb.coupon_system.enums.ClientType;
import com.jb.coupon_system.security.Info;
import com.jb.coupon_system.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final Map<UUID, Info> map;

    @Override
    public UUID createToken(ClientService client, ClientType clientType, int clientId) {
        UUID token = UUID.randomUUID();
        LocalDateTime time = LocalDateTime.now();
        ClientType type = clientType;

        Info info = Info.builder()
                .id(clientId)
                .clientType(clientType)
                .time(time)
                .build();
        map.put(token, info);
        return token;
    }

    @Override
    public void clearTokens() {

    }

    @Override
    public boolean isValid(UUID token, ClientType clientType) {
        return false;
    }

    @Override
    public int getUserId(UUID token) {
        return 0;
    }
}

package com.jb.coupon_system.services.token_servic;

import com.jb.coupon_system.enums.ClientType;
import com.jb.coupon_system.enums.ErrorMsg;
import com.jb.coupon_system.exceptions.CouponSystemException;
import com.jb.coupon_system.security.Info;
import com.jb.coupon_system.services.ClientService;
import com.jb.coupon_system.utils.ServiceUtils;
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

        map.put(token,
                Info.builder()
                        .id(clientId)
                        .clientType(clientType)
                        .time(LocalDateTime.now())
                        .build());

        return token;
    }

    @Override
    public void clearTokens() {
        map.values().removeIf(info -> ServiceUtils.isTokenExpired(info.getTime()));
    }

    @Override
    public boolean isTokenValid(UUID token, ClientType clientType) throws CouponSystemException {
        Info info = map.get(token);
        if (info == null) {
            throw new CouponSystemException(ErrorMsg.ACCESS_DENIED);
        }
        return info.getClientType().equals(clientType);
    }

    @Override
    public int getUserId(UUID token) throws CouponSystemException {
        Info info = map.get(token);
        if (info == null) {
            throw new CouponSystemException(ErrorMsg.ACCESS_DENIED);
        }
        return info.getId();
    }
}

package com.jb.coupon_system.dto;

import com.jb.coupon_system.enums.ClientType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LoginReqDto {

    private String email;
    private String password;
    private ClientType clientType;
}

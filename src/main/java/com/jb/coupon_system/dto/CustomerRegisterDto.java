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
public class CustomerRegisterDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String confirmPassword;
}

package com.jb.coupon_system.security;

import com.jb.coupon_system.enums.ClientType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Info {

    private int id;
    private ClientType clientType;
    private LocalDateTime time;
}

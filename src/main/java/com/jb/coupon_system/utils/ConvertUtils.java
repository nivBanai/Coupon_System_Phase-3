package com.jb.coupon_system.utils;

import com.jb.coupon_system.dto.ClientInfoDto;

import java.util.Map;

public class ConvertUtils {


    public static ClientInfoDto convertToClientInfoDto(Map<String, Object> info) {
        return ClientInfoDto.builder()
                .id((int) info.get("id"))
                .name((String) info.get("name"))
                .profilePic((String) info.get("profile_pic"))
                .build();
    }
}

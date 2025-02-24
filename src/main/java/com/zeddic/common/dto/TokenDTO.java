package com.zeddic.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author: zeddic
 * @description:
 * @date: 2025/1/25 下午7:54
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TokenDTO {
    private String tokenName;
    private String tokenValue;
    private Long timeout;
}

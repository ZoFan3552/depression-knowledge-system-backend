package com.zeddic.common.dto;

import lombok.*;

/**
 * @author Zeddic
 * @description:
 * @date: 2025/1/25 下午7:54
 */
@Data
@AllArgsConstructor
public class TokenDTO {
    private String tokenName;
    private String tokenValue;
    private Long timeout;
}

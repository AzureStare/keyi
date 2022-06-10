package com.shaoshi.yixue.entity;

import lombok.Data;

/**
 * @author wanglh
 * @date 2022/4/17 14:22
 */
@Data
public class NextFortuneRequest {
    private String curFortune;
    private Integer count;
}

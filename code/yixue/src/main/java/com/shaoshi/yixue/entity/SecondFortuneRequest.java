package com.shaoshi.yixue.entity;

import lombok.Data;

/**
 * @author wanglh
 * @date 2022/4/17 17:59
 */
@Data
public class SecondFortuneRequest {
    private Integer year;
    private String fortune;
    private Integer offset;
}

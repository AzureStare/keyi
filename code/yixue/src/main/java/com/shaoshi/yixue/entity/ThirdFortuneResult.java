package com.shaoshi.yixue.entity;

import lombok.Data;

import java.util.List;

/**
 * @author wanglh
 * @date 2022/4/17 18:25
 */
@Data
public class ThirdFortuneResult {
    private String fortune;
    private List<String> fourthFortunes;
}

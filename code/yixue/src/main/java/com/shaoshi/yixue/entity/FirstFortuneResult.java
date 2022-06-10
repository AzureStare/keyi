package com.shaoshi.yixue.entity;

import lombok.Data;

import javax.persistence.Column;

/**
 * @author wanglh
 * @date 2022/4/17 17:45
 */
@Data
public class FirstFortuneResult {
    private String fortune;
    private Integer year;
    private String ys;
    private String gy;
    private String xz;
    private String cs;
    private String sj;
    private String dz;
}

package com.shaoshi.yixue.entity;

import lombok.Data;

import javax.persistence.Column;

/**
 * @author wanglh
 * @date 2022/4/17 14:54
 */
@Data
public class ChineseCalendar {
    private Integer year;
    private String dz;
    private String ys;
    private String gy;
    private String xz;
    private String cs;
    private String sj;
}

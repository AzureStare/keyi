package com.shaoshi.yixue.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author wanglh
 * @date 2022/4/17 15:21
 */
@Data
@NoArgsConstructor
@Entity(name = "perpetual_calendar_str")
public class CalendarStr implements Serializable {
    private static final long serialVersionUID = 2003452869936985841L;
    @Id
    @Column(name = "year")
    private Integer year;

    @Column(name = "ys")
    private String ys;
    @Column(name = "gy")
    private String gy;
    @Column(name = "xz")
    private String xz;
    @Column(name = "cs")
    private String cs;
    @Column(name = "sj")
    private String sj;
    @Column(name = "dz")
    private String dz;
}


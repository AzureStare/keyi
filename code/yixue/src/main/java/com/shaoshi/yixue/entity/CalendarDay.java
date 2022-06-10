package com.shaoshi.yixue.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author wanglh
 * @date 2022/4/9 20:50
 */
@Data
@NoArgsConstructor
@Entity(name = "perpetual_calendar_day")
public class CalendarDay implements Serializable {
    private static final long serialVersionUID = -3474447891348657892L;
    @Id
    @Column(name = "year")
    private Integer year;
    @Column(name = "dz")
    private Long dz;
    @Column(name = "ys")
    private Long ys;
    @Column(name = "gy")
    private Long gy;
    @Column(name = "xz")
    private Long xz;
    @Column(name = "cs")
    private Long cs;
    @Column(name = "sj")
    private Long sj;
}

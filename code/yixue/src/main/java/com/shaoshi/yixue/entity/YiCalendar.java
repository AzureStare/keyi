package com.shaoshi.yixue.entity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author wanglh
 * @date 2022/4/9 14:36
 */
@Data
@NoArgsConstructor
@Entity(name = "perpetual_calendar")
public class YiCalendar implements Serializable {
    private static final long serialVersionUID = -4502373746936594580L;
    @Id
    @Column(name = "year")
    private Integer year;
    @Column(name = "dz")
    private String dz;
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
}

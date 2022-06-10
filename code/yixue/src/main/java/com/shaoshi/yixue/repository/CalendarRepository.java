package com.shaoshi.yixue.repository;

import com.shaoshi.yixue.entity.YiCalendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author wanglh
 * @date 2022/4/9 14:57
 */
public interface CalendarRepository  extends JpaRepository<YiCalendar,Integer>, JpaSpecificationExecutor<YiCalendar> {
}

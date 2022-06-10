package com.shaoshi.yixue.controller;

import com.shaoshi.yixue.common.JResponse;
import com.shaoshi.yixue.entity.*;
import com.shaoshi.yixue.service.FortuneService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

/**
 * @author wanglh
 * @date 2022/4/9 13:33
 */
@Slf4j
@RestController
@RequestMapping("/fortune-service")
public class FortuneController {
    @Autowired
    private FortuneService fortuneService;
    @Operation(summary = "生成你的易学名片")
    @PostMapping("generateYourCard/{year}/{month}/{day}/{hour}")
    public JResponse<FortuneCard> generateYourCard(@PathVariable int year, @PathVariable int month, @PathVariable int day, @PathVariable int hour) {
        log.info("generateYourCard begin year:{}, month:{}, day:{}, hour:{}", year, month, day, hour);
        return fortuneService.generateYourCard(year, month, day, hour);
    }

    @Operation(summary = "下一n卦")
    @PostMapping("nextFortune")
    public JResponse<String> nextFortune(@RequestBody NextFortuneRequest request) {
        log.info("nextFortune begin curFortune:{}, count:{}",request.getCurFortune(), request.getCount());
        return fortuneService.nextFortune(request.getCurFortune(), request.getCount());
    }

    @Operation(summary = "根据年份算出第一卦")
    @PostMapping("firstFortuneByYear/{year}")
    public JResponse<FirstFortuneResult> firstFortuneByYear(@PathVariable int year) {
        log.info("firstFortuneByYear begin year:{}",year);
        return fortuneService.firstFortuneByYear(year);
    }

    @Operation(summary = "根据偏移算出第二卦")
    @PostMapping("secondFortuneByOffset")
    public JResponse<SecondFortuneResult> secondFortuneByOffset(@RequestBody SecondFortuneRequest request) {
        log.info("secondFortuneByOffset begin Request:{}",request.toString());
        return fortuneService.secondFortuneByOffset(request);
    }

    @Operation(summary = "根据偏移算出第三卦")
    @PostMapping("thirdFortuneByOffset")
    public JResponse<ThirdFortuneResult> thirdFortuneByOffset(@RequestBody ThirdFortuneRequest request) {
        log.info("secondFortuneByOffset begin Request:{}",request.toString());
        return fortuneService.thirdFortuneByOffset(request);
    }

    @Operation(summary = "初始化表数据")
    @PostMapping("initTable")
    public JResponse loadData() {
        log.info("initTable begin");
        //return fortuneService.loadDbFromfile();
        JResponse jResponse = fortuneService.loadCalendarDayTable();
        if (jResponse.getRet() != 1) {
            return jResponse;
        }else {
            return fortuneService.loadCalendarStrTable();
        }
    }

    @Operation(summary = "某一年的节气日期")
    @PostMapping("calendarByYear/{year}")
    public JResponse<CalendarStr> calendarByYear(@PathVariable int year) {
        log.info("calendarByYear begin year:{}",year);
        return fortuneService.getCalendarStrByYear(year);
    }

    @Operation(summary = "上传图片到ipfs")
    @PostMapping("uploadFileToIPFS")
    public JResponse<String> uploadFileToIPFS() {
        log.info("uploadFileToIPFS begin");
        return fortuneService.uploadFileToIPFS();
    }
}

package com.shaoshi.yixue;

import com.shaoshi.yixue.common.JResponse;
import com.shaoshi.yixue.controller.FortuneController;
import com.shaoshi.yixue.entity.FortuneCard;
import com.shaoshi.yixue.entity.YiCalendar;
import com.shaoshi.yixue.entity.CalendarDay;
import com.shaoshi.yixue.repository.CalendarDayRepository;
import com.shaoshi.yixue.repository.CalendarRepository;
import com.shaoshi.yixue.service.FortuneService;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.FileInputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author wanglh
 * @date 2022/4/9 14:34
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = Application.class)
public class FortuneTest {

    @Autowired
    private CalendarRepository calendarRepository;

    @Autowired
    private CalendarDayRepository calendarDayRepository;

    @Autowired
    private FortuneService fortuneService;

    @Test
    public void saveRecord() {
        YiCalendar calendar = new YiCalendar();
        calendar.setYear(1802);
        calendar.setDz("12-22");
        calendar.setYs("01-20");
        calendar.setGy("04-24");
        calendar.setXz("06-20");
        calendar.setCs("08-10");
        calendar.setSj("10-12");
        try {
            calendarRepository.saveAndFlush(calendar);
            YiCalendar cal = calendarRepository.findById(1902).get();
            assert(cal.getCs().equals("08-10"));
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void loadData() {
        try {
            FileInputStream fileInput = new FileInputStream("D:\\周易\\输出1.xlsx");//创建文件输入流
            XSSFWorkbook wb = new XSSFWorkbook(fileInput);//由输入流文件得到工作簿对象
            XSSFSheet sheet = wb.getSheetAt(0);//获取第一个sheet
            int lastRowNum = sheet.getLastRowNum();
            System.out.println(lastRowNum);
            YiCalendar cal = new YiCalendar();
            Integer y = 0;
            String jq = "";
            for (int i = 2; i <= lastRowNum; ++i) {
                XSSFRow row = sheet.getRow(i);
                XSSFCell cell0 = row.getCell(0);
                XSSFCell cell1 = row.getCell(1);
                String time = cell0.getStringCellValue();
                y = Integer.valueOf(time.substring(0, 4));
                String jieqi = cell1.getStringCellValue().trim();
                if (i==2) {
                    cal.setYear(1900);
                    cal.setDz("12-23");
                    jq = "冬至";
                }else {
                    if (!jieqi.equals(jq)) {
                        System.out.println(time);
                        System.out.println("year"+ y.toString());
                        System.out.println(jieqi);
                        if (!y.equals(cal.getYear())) {
                            calendarRepository.saveAndFlush(cal);
                            System.out.println(cal.toString());
                            cal.setYear(y);
                            cal.setDz(null);
                            cal.setYs(null);
                            cal.setGy(null);
                            cal.setXz(null);
                            cal.setCs(null);
                            cal.setSj(null);
                        }
                        if (jieqi.equals("冬至")) {
                            cal.setDz(time.substring(5,10));
                        }else if (jieqi.equals("雨水")) {
                            cal.setYs(time.substring(5,10));
                        }else if (jieqi.equals("谷雨")) {
                            cal.setGy(time.substring(5,10));
                        }else if (jieqi.equals("夏至")) {
                            cal.setXz(time.substring(5,10));
                        }else if (jieqi.equals("处暑")) {
                            cal.setCs(time.substring(5,10));
                        }else if (jieqi.equals("霜降")) {
                            cal.setSj(time.substring(5,10));
                        }
                        jq = jieqi;
                    }
                }
            }
            calendarRepository.saveAndFlush(cal);
            wb.close();
            fileInput.close();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void loadDayData() {
        try {
            FileInputStream fileInput = new FileInputStream("data.xlsx");//创建文件输入流
            XSSFWorkbook wb = new XSSFWorkbook(fileInput);//由输入流文件得到工作簿对象
            XSSFSheet sheet = wb.getSheetAt(0);//获取第一个sheet
            int lastRowNum = sheet.getLastRowNum();
            System.out.println(lastRowNum);
            CalendarDay cal = new CalendarDay();
            Integer y = 0;
            String jq = "";
            for (int i = 2; i <= lastRowNum; ++i) {
                XSSFRow row = sheet.getRow(i);
                XSSFCell cell0 = row.getCell(0);
                XSSFCell cell1 = row.getCell(1);
                String time = cell0.getStringCellValue();
                y = Integer.valueOf(time.substring(0, 4));
                String jieqi = cell1.getStringCellValue().trim();
                if (i==2) {
                    cal.setYear(1900);
                    cal.setDz(getDay("1900-12-22"));
                    jq = "冬至";
                }else {
                    if (!jieqi.equals(jq)) {
                        System.out.println(time);
                        System.out.println("year"+ y.toString());
                        System.out.println(jieqi);
                        if (!y.equals(cal.getYear())) {
                            calendarDayRepository.saveAndFlush(cal);
                            System.out.println(cal.toString());
                            cal.setYear(y);
                            cal.setDz(null);
                            cal.setYs(null);
                            cal.setGy(null);
                            cal.setXz(null);
                            cal.setCs(null);
                            cal.setSj(null);
                        }
                        if (jieqi.equals("冬至")) {
                            cal.setDz(getDay(time.substring(0,10)));
                        }else if (jieqi.equals("雨水")) {
                            cal.setYs(getDay(time.substring(0,10)));
                        }else if (jieqi.equals("谷雨")) {
                            cal.setGy(getDay(time.substring(0,10)));
                        }else if (jieqi.equals("夏至")) {
                            cal.setXz(getDay(time.substring(0,10)));
                        }else if (jieqi.equals("处暑")) {
                            cal.setCs(getDay(time.substring(0,10)));
                        }else if (jieqi.equals("霜降")) {
                            cal.setSj(getDay(time.substring(0,10)));
                        }
                        jq = jieqi;
                    }
                }
            }
            calendarDayRepository.saveAndFlush(cal);
            wb.close();
            fileInput.close();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void testLoadTable() {
        fortuneService.loadCalendarStr();
    }
    private Long getDay(String dayStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date day = sdf.parse(dayStr);
            Date sday = sdf.parse("1900-12-22");
            return (day.getTime() - sday.getTime())/(24*60*60*1000);
        }catch (Exception e) {
            return -1l;
        }
    }

    @Test
    public void testDay() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date edate = sdf.parse("1903-04-23");
            Date sdate = sdf.parse("1902-04-20");
            long day = (edate.getTime()-sdate.getTime())/(24*60*60*1000);
            System.out.println(day);
            Date dd = sdf.parse("1903-04-23");
            System.out.println(dd.getTime()/(24*60*60*1000));

            Date curDate = new Date();
            Calendar calendar = Calendar.getInstance();
            System.out.println("Year: ");
            String year = curDate.toString();
            year = year.substring(year.length()-4);
            System.out.println(year);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    @Test
    public void testFortune() {
        JResponse<FortuneCard> response = fortuneService.generateYourCard(1985, 2, 28, 9);
        System.out.println(response.toString());

    }

    @Test
    public void getLastDayOfYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, 2022);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();
        System.out.println(currYearLast.toString());
    }

    @Test
    public void hexStr() {
        String temp = "449d4fd4a7c017e9ce5586a6e7fc29c7b6bda63ee454910822ad45d003cf3723";
        BigInteger num = new BigInteger(temp, 16);
        byte[] bytes =  num.toByteArray();
        String tstr = new String(bytes, StandardCharsets.UTF_16LE);
        BigInteger n = new BigInteger(tstr.getBytes());
        System.out.println(n.toString(16));
        System.out.println(bytes);
        byte[] bytes1 = new byte[]{0, -52, -39, -8, 44, 68, 12, 126, 23, 46, 97, 31, -91, -9, 126, -60, 9, 11, 10, -35, -37, -51, 87, -95, 54, -89, -122, 86, 108, 26, -78, 127, 49};
        BigInteger nn = new BigInteger(bytes1);
        System.out.println(nn.toString(16));
    }
}

package com.shaoshi.yixue.service;

import com.shaoshi.yixue.common.JResponse;
import com.shaoshi.yixue.entity.*;
import com.shaoshi.yixue.repository.CalendarDayRepository;
import com.shaoshi.yixue.repository.CalendarRepository;
import com.shaoshi.yixue.repository.CalendarStrRepository;
import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.compat.JreCompat;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.PostConstruct;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author wanglh
 * @date 2022/4/9 14:28
 */
@Service
@Slf4j
public class FortuneService {
    @Autowired
    private CalendarDayRepository calendarDayRepository;
    @Autowired
    private CalendarStrRepository calendarStrRepository;
    private static String[] allFortuneStr ={"复","颐","屯","益","震","噬嗑","随","无妄", "明夷","贲","既济","家人","丰","离","革","同人","临","损","节","中孚","归妹","睽","兑","履","泰","大畜","需",
                                         "小畜","大壮","大有","夬","乾","姤","大过","鼎","恒","巽","井","蛊","升","讼","困","未济","解","涣","坎","蒙","师","遁","咸","旅","小过","渐","蹇","艮",
                                         "谦","否","萃","晋","豫","观","比","剥","坤"};
    private static int[] allFortuneNum = {32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,31,30,29,28,27,26,25,24,23,22,21,20,19,18,17,16,15,
                                       14,13,12,11,10,9,8,7,6,5,4,3,2,1,0};
    private static String[] fortuneStr ={"复","颐","屯","益","震","噬嗑","随","无妄", "明夷","贲","既济","家人","丰","革","同人","临","损","节","中孚","归妹","睽","兑","履","泰","大畜","需",
            "小畜","大壮","大有","夬","姤","大过","鼎","恒","巽","井","蛊","升","讼","困","未济","解","涣","蒙","师","遁","咸","旅","小过","渐","蹇","艮",
            "谦","否","萃","晋","豫","观","比","剥"};
    private static int[] fortuneNum = {32,33,34,35,36,37,38,39,40,41,42,43,44,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,
                                       31,30,29,28,27,26,25,24,23,22,21,20,19,17,16,15, 14,13,12,11,10,9,8,7,6,5,4,3,2,1};
    //private static io.ipfs.api.IPFS ipfs = new IPFS("/ip4/127.0.0.1/tcp/5001");
    private static String qian ="乾";
    private static String kun = "坤";
    private static String kan = "坎";
    private static String li = "离";
    private static int qNum = 63;
    private static int kunNum = 0;
    private static int kanNum = 18;
    private static int liNum = 45;
    private HashMap<String, Integer> allFortuneStrIndexMap;
    private HashMap<Integer, Integer> allFortuneNumIndexMap;
    private HashMap<String, Integer> fortuneStrIndexMap;
    private HashMap<Integer, Integer> fortuneNumIndexMap;

    @PostConstruct
    private void initMap() {
        allFortuneStrIndexMap = new HashMap<>();
        for (int i=0; i< allFortuneStr.length; i++) {
            allFortuneStrIndexMap.put(allFortuneStr[i], i);
        }
        allFortuneNumIndexMap = new HashMap<>();
        for (int i=0; i< allFortuneNum.length; i++) {
            allFortuneNumIndexMap.put(allFortuneNum[i], i);
        }

        fortuneStrIndexMap = new HashMap<>();
        for (int i=0; i< fortuneStr.length; i++) {
            fortuneStrIndexMap.put(fortuneStr[i], i);
        }
        fortuneNumIndexMap = new HashMap<>();
        for (int i=0; i< fortuneNum.length; i++) {
            fortuneNumIndexMap.put(fortuneNum[i], i);
        }
    }

    public JResponse loadCalendarDayTable() {
        log.info("begin loadCalendarDayTable");
        try {
            FileInputStream fileInput = new FileInputStream("calendar_day.xlsx");//创建文件输入流
            XSSFWorkbook wb = new XSSFWorkbook(fileInput);//由输入流文件得到工作簿对象
            XSSFSheet sheet = wb.getSheetAt(0);//获取第一个sheet
            int lastRowNum = sheet.getLastRowNum();
            CalendarDay cal = new CalendarDay();
            for (int i = 0; i <= lastRowNum; ++i) {
                XSSFRow row = sheet.getRow(i);
                XSSFCell cell0 = row.getCell(0);
                XSSFCell cell1 = row.getCell(1);
                XSSFCell cell2 = row.getCell(2);
                XSSFCell cell3 = row.getCell(3);
                XSSFCell cell4 = row.getCell(4);
                XSSFCell cell5 = row.getCell(5);
                XSSFCell cell6 = row.getCell(6);
                cal.setYear(Integer.valueOf(cell0.getStringCellValue().trim()));
                if (!cell1.getStringCellValue().trim().equals(""))
                    cal.setYs(Long.valueOf(cell1.getStringCellValue().trim()));
                if (!cell2.getStringCellValue().trim().equals(""))
                    cal.setGy(Long.valueOf(cell2.getStringCellValue().trim()));
                if (!cell3.getStringCellValue().trim().equals(""))
                    cal.setXz(Long.valueOf(cell3.getStringCellValue().trim()));
                if (!cell4.getStringCellValue().trim().equals(""))
                    cal.setCs(Long.valueOf(cell4.getStringCellValue().trim()));
                if (!cell5.getStringCellValue().trim().equals(""))
                    cal.setSj(Long.valueOf(cell5.getStringCellValue().trim()));
                if (!cell6.getStringCellValue().trim().equals(""))
                    cal.setDz(Long.valueOf(cell6.getStringCellValue().trim()));
                calendarDayRepository.saveAndFlush(cal);
            }
            wb.close();
            fileInput.close();
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return JResponse.fail("load data failed "+ e.getMessage());
        }
        return JResponse.success();
    }

    public JResponse loadCalendarStrTable() {
        log.info("begin loadCalendarStrTable");
        try {
            FileInputStream fileInput = new FileInputStream("calendar_str.xlsx");//创建文件输入流
            XSSFWorkbook wb = new XSSFWorkbook(fileInput);//由输入流文件得到工作簿对象
            XSSFSheet sheet = wb.getSheetAt(0);//获取第一个sheet
            int lastRowNum = sheet.getLastRowNum();
            CalendarStr cal = new CalendarStr();
            for (int i = 0; i <= lastRowNum; ++i) {
                XSSFRow row = sheet.getRow(i);
                XSSFCell cell0 = row.getCell(0);
                XSSFCell cell1 = row.getCell(1);
                XSSFCell cell2 = row.getCell(2);
                XSSFCell cell3 = row.getCell(3);
                XSSFCell cell4 = row.getCell(4);
                XSSFCell cell5 = row.getCell(5);
                XSSFCell cell6 = row.getCell(6);
                cal.setYear(Integer.valueOf(cell0.getStringCellValue().trim()));
                if (!cell1.getStringCellValue().trim().equals(""))
                    cal.setYs(cell1.getStringCellValue().trim());
                if (!cell2.getStringCellValue().trim().equals(""))
                    cal.setGy(cell2.getStringCellValue().trim());
                if (!cell3.getStringCellValue().trim().equals(""))
                    cal.setXz(cell3.getStringCellValue().trim());
                if (!cell4.getStringCellValue().trim().equals(""))
                    cal.setCs(cell4.getStringCellValue().trim());
                if (!cell5.getStringCellValue().trim().equals(""))
                    cal.setSj(cell5.getStringCellValue().trim());
                if (!cell6.getStringCellValue().trim().equals(""))
                    cal.setDz(cell6.getStringCellValue().trim());
                calendarStrRepository.saveAndFlush(cal);
            }
            wb.close();
            fileInput.close();
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return JResponse.fail("load data failed "+ e.getMessage());
        }
        return JResponse.success();
    }

    public JResponse loadCalendarStr() {
        log.info("begin loadData");
        try {
            FileInputStream fileInput = new FileInputStream("data.xlsx");//创建文件输入流
            XSSFWorkbook wb = new XSSFWorkbook(fileInput);//由输入流文件得到工作簿对象
            XSSFSheet sheet = wb.getSheetAt(0);//获取第一个sheet
            int lastRowNum = sheet.getLastRowNum();
            log.info("load file end");
            System.out.println(lastRowNum);
            CalendarStr cal = new CalendarStr();
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
                    cal.setDz("12-22");
                    jq = "冬至";
                }else {
                    if (!jieqi.equals(jq)) {
                        System.out.println(time);
                        System.out.println("year"+ y.toString());
                        System.out.println(jieqi);
                        if (!y.equals(cal.getYear())) {
                            calendarStrRepository.saveAndFlush(cal);
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
            calendarStrRepository.saveAndFlush(cal);
            log.info("loadData finished");
            wb.close();
            fileInput.close();
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return JResponse.fail("load data failed "+ e.getMessage());
        }
        return JResponse.success();
    }

    public JResponse loadData() {
        log.info("begin loadData");
        try {
            FileInputStream fileInput = new FileInputStream("data.xlsx");//创建文件输入流
            XSSFWorkbook wb = new XSSFWorkbook(fileInput);//由输入流文件得到工作簿对象
            XSSFSheet sheet = wb.getSheetAt(0);//获取第一个sheet
            int lastRowNum = sheet.getLastRowNum();
            log.info("load file end");
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
            log.info("loadData finished");
            wb.close();
            fileInput.close();
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return JResponse.fail("load data failed "+ e.getMessage());
        }
        return JResponse.success();
    }

    public JResponse<FirstFortuneResult> firstFortuneByYear(int year) {
        if (year <1901 || year > 2100) {
            JResponse<FirstFortuneResult> response = JResponse.fail("参数非法", "1002", null);
            return response;
        }
        FirstFortuneResult firstFortuneResult = new FirstFortuneResult();
        int startIndex = 0;
        int startYear = 0;
        if (year > 2043) {
            startIndex = 31;
            startYear = 2044;
        }else if (year > 1983) {
            startIndex = 32;
            startYear = 1984;
        }else if (year > 1923){
            startIndex = 34;
            startYear = 1924;
        }else {
            startIndex =38;
            startYear = 1864;
        }
        int index = (year - startYear + startIndex) % 60;
        firstFortuneResult.setFortune(fortuneStr[index]);
        firstFortuneResult.setYear(year);
        CalendarStr calendarStr = calendarStrRepository.findById(year).get();
        firstFortuneResult.setYs(calendarStr.getYs());
        firstFortuneResult.setGy(calendarStr.getGy());
        firstFortuneResult.setXz(calendarStr.getXz());
        firstFortuneResult.setCs(calendarStr.getCs());
        firstFortuneResult.setSj(calendarStr.getSj());
        firstFortuneResult.setDz(calendarStr.getDz());
        return JResponse.success(firstFortuneResult);
    }

    public JResponse<SecondFortuneResult> secondFortuneByOffset(SecondFortuneRequest request) {
        SecondFortuneResult secondFortuneResult = new SecondFortuneResult();
        secondFortuneResult.setEndYear(request.getYear());
        if (request.getOffset() == 1) {
            secondFortuneResult.setStartYear(request.getYear() - 1);
        }else {
            secondFortuneResult.setStartYear(request.getYear());
        }
        int offset = request.getOffset();
        if (offset >= 7) {
            offset =1;
        }
        int index = allFortuneStrIndexMap.get(request.getFortune());
        int val = allFortuneNum[index]^(1<<(6-offset));
        int index2 = findAllIndexByVal(val);
        CalendarStr curCalendarStr = calendarStrRepository.findById(request.getYear()).get();
        secondFortuneResult.setFortune(allFortuneStr[index2]);
        if (request.getOffset() == 1) {
            CalendarStr preCalendarStr = calendarStrRepository.findById(request.getYear() - 1).get();
            secondFortuneResult.setStartDay(preCalendarStr.getDz());
            secondFortuneResult.setEndDay(curCalendarStr.getYs());
        }else if (request.getOffset() == 2) {
            secondFortuneResult.setStartDay(curCalendarStr.getYs());
            secondFortuneResult.setEndDay(curCalendarStr.getGy());
        }else if (request.getOffset() == 3) {
            secondFortuneResult.setStartDay(curCalendarStr.getGy());
            secondFortuneResult.setEndDay(curCalendarStr.getXz());
        }else if (request.getOffset() == 4) {
            secondFortuneResult.setStartDay(curCalendarStr.getXz());
            secondFortuneResult.setEndDay(curCalendarStr.getCs());
        }else if (request.getOffset() == 5) {
            secondFortuneResult.setStartDay(curCalendarStr.getCs());
            secondFortuneResult.setEndDay(curCalendarStr.getSj());
        }else if (request.getOffset() == 6) {
            secondFortuneResult.setStartDay(curCalendarStr.getSj());
            secondFortuneResult.setEndDay(curCalendarStr.getDz());
        }else {
            secondFortuneResult.setStartDay(curCalendarStr.getDz());
            secondFortuneResult.setEndDay("12-31");
        }
        return JResponse.success(secondFortuneResult);
    }

    public JResponse<ThirdFortuneResult> thirdFortuneByOffset(@RequestBody ThirdFortuneRequest request) {
        ThirdFortuneResult thirdFortuneResult = new ThirdFortuneResult();
        thirdFortuneResult.setFourthFortunes(new ArrayList<String>());
        int startIndex;
        if (isSpecialFortune(request.getFortune())) {
            startIndex = allFortuneStrIndexMap.get(request.getFortune());
            int val = allFortuneNum[(startIndex + 1)%64];
            startIndex = fortuneNumIndexMap.get(val);
        }else {
            startIndex = fortuneStrIndexMap.get(request.getFortune());
        }
        int index = (request.getOffset() - 1 + startIndex) % 60;
        String fortune = fortuneStr[index];
        thirdFortuneResult.setFortune(fortune);
        for (int i=1; i<=6; i++) {
            int val = fortuneNum[index]^(1<<(6-i));
            int index1 = allFortuneNumIndexMap.get(val);
            thirdFortuneResult.getFourthFortunes().add(allFortuneStr[index1]);
        }
        return JResponse.success(thirdFortuneResult);
    }

    public JResponse<String> nextFortune(String curFortune, Integer count) {
        int startIndex;
        if (isSpecialFortune(curFortune)) {
            startIndex = allFortuneStrIndexMap.get(curFortune);
            int val = allFortuneNum[(startIndex + 1)%64];
            startIndex = fortuneNumIndexMap.get(val);
        }else {
            startIndex = fortuneStrIndexMap.get(curFortune);
        }
        int index = (count + startIndex) % 60;
        return JResponse.success(fortuneStr[index]);
    }
    public JResponse<FortuneCard> generateYourCard(int year, int month, int day, int hour) {
        if (!isValidateTime(year, month, day, hour)) {
            JResponse<FortuneCard> response = JResponse.fail("参数非法", "1002", null);
            return response;
        }
        Long dayTime = getDay(year, month, day);
        FortuneCard fortuneCard = new FortuneCard();
        int startIndex = 0;
        int startYear = 0;
        if (year > 2043) {
            startIndex = 31;
            startYear = 2044;
        }else if (year > 1983) {
            startIndex = 32;
            startYear = 1984;
        }else if (year > 1923){
            startIndex = 34;
            startYear = 1924;
        }else {
            startIndex =38;
            startYear = 1864;
        }
        int index1 = (year - startYear + startIndex) % 60;
        fortuneCard.setTianshi(fortuneStr[index1]);

        CalendarDay calendar = calendarDayRepository.findById(year).get();
        int offsetForIndex2 = 0;
        Long dayForIndex3;
        if (dayTime > calendar.getDz()) {
            offsetForIndex2 = 1;
            dayForIndex3 = dayTime - calendar.getDz();
        }else if (dayTime > calendar.getSj()) {
            offsetForIndex2 = 6;
            dayForIndex3 = dayTime - calendar.getSj();
        }
        else if (dayTime > calendar.getCs()) {
            offsetForIndex2 = 5;
            dayForIndex3 = dayTime - calendar.getCs();
        }
        else if (dayTime > calendar.getXz()) {
            offsetForIndex2 = 4;
            dayForIndex3 = dayTime - calendar.getXz();
        }
        else if (dayTime > calendar.getGy()) {
            offsetForIndex2 = 3;
            dayForIndex3 = dayTime - calendar.getGy();
        }else if (dayTime > calendar.getYs()) {
            offsetForIndex2 = 2;
            dayForIndex3 = dayTime - calendar.getYs();
        }else {
            offsetForIndex2 = 1;
            CalendarDay preCal = calendarDayRepository.findById(year - 1).get();
            dayForIndex3 = dayTime - preCal.getDz();
        }

        int val = fortuneNum[index1]^(1<<(6-offsetForIndex2));

        int index2 = findAllIndexByVal(val);
        fortuneCard.setDili(allFortuneStr[index2]);
        int index2ForNext = 0;
        if (val == qNum || val == kunNum || val == kanNum || val == liNum) {
            index2ForNext = findIndexByVal(allFortuneNum[(index2+1)%64]);
        }else {
            index2ForNext = findIndexByVal(allFortuneNum[index2]);
        }

        int index3 = (dayForIndex3.intValue() - 1 + index2ForNext) % 60;
        fortuneCard.setShengli(fortuneStr[index3]);

        int offsetForIndex4;
        offsetForIndex4 = (hour + 4) / 4;

        val = fortuneNum[index3]^(1<<(6-offsetForIndex4));

        int index4 = findAllIndexByVal(val);
        fortuneCard.setSijie(allFortuneStr[index4]);

        int thisYear = getThisYear();

        if (thisYear > 2043) {
            startIndex = 31;
            startYear = 2044;
        }else{
            startIndex = 32;
            startYear = 1984;
        }

        int index5 = (startIndex + thisYear - startYear) % 60;
        int val5 = fortuneNum[index5]^(1<<(6-offsetForIndex2));
        val5 = val5^(1<<(6-offsetForIndex4));

        index5 = findAllIndexByVal(val5);
        fortuneCard.setYungua(allFortuneStr[index5]);

    return JResponse.success(fortuneCard);
    }

    public JResponse<CalendarStr> getCalendarStrByYear(@PathVariable int year) {
        CalendarStr calendarStr = calendarStrRepository.findById(year).get();
        return  JResponse.success(calendarStr);
    }

    public JResponse<String> uploadFileToIPFS() {
        try {
            //io.ipfs.api.IPFS ipfs = new IPFS("/ip4/127.0.0.1/tcp/5001");
            //NamedStreamable.FileWrapper file = new NamedStreamable.FileWrapper(new File("D:\\ipfstests.jpg"));
            //MerkleNode addResult = ipfs.add(file).get(0);
            //return JResponse.success(addResult.hash.toString());
            return null;
        }catch (Exception e) {
            return null;
        }
    }

    private int getThisYear() {
        Date date = new Date();
        String year = date.toString();
        year = year.substring(year.length() - 4);
        return Integer.valueOf(year);
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

    private Long getDay(int year, int month, int day) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dayStr = String.valueOf(year);
            if (month < 10) {
                dayStr = String.format("%s-0%d",dayStr, month);
            }else {
                dayStr = String.format("%s-%d",dayStr, month);
            }
            if (day < 10) {
                dayStr = String.format("%s-0%d",dayStr, day);
            }else {
                dayStr = String.format("%s-%d",dayStr, day);
            }
            Date eday = sdf.parse(dayStr);
            Date sday = sdf.parse("1900-12-22");
            return (eday.getTime() - sday.getTime())/(24*60*60*1000);
        }catch (Exception e) {
            return -1l;
        }
    }

    private int findIndexByVal(int value) {
        for (int i=0; i<fortuneNum.length; i++) {
            if (value == fortuneNum[i]) {
                return i;
            }
        }
        return 0;
    }

    private int findAllIndexByVal(int value) {
        for (int i=0; i<allFortuneNum.length; i++) {
            if (value == allFortuneNum[i]) {
                return i;
            }
        }
        return 0;
    }

    private boolean isSpecialFortune(String fortune) {
        if (fortune.equals(qian) || fortune.equals(kun) || fortune.equals(kan) || fortune.equals(li)) {
            return true;
        }else {
            return false;
        }

    }

    private boolean isValidateTime(int year, int month, int day, int hour) {
        if (hour < 0 || hour > 23)
            return false;
        if (year < 1901 || year > 2100)
            return false;
        if (month < 1 || month > 12)
            return false;
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            if (day < 1 || day > 30)
                return false;
        }else if (month == 2) {
            if ((year % 400 == 0) ||((year % 4 == 0) && (year % 100 != 0))) {
                if (day < 1 || day > 29)
                    return false;
            }else {
                if (day < 1 || day > 28)
                    return false;
            }
        }else {
            if (day < 1 || day > 31)
                return false;
        }
        return true;
    }
}

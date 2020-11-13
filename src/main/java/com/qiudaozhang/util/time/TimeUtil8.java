package com.qiudaozhang.util.time;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author 邱道长
 * 2020/10/20
 * 日期工具类
 */
public class TimeUtil8 {

    /**
     * 获取当前时间（日期+时间）
     * @return
     */
    public static LocalDateTime currDateTime(){
       return LocalDateTime.now();
    }


    /**
     * 默认的区域id
     * @return
     */
    public static ZoneId defaultZoneId(){
        return ZoneId.systemDefault();
    }


    /**
     * 当天开始
     * @return
     */
    public static LocalDateTime todayBegin(){
        LocalDate localDate = LocalDate.now();
        return localDate.atStartOfDay();
    }


    /**
     * 当年的开始
     * @return
     */
    public static LocalDateTime yearBegin(){
       return LocalDateTime.of(currDateTime().getYear(),1,1,0,0,0);
    }






    public static void main(String[] args) {
        System.out.println(yearBegin());
    }
}

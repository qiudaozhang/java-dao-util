package com.qiudaozhang.util.time;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author 邱道长
 * 2020/10/20
 * 日期工具类
 */
public class TimeUtil {

    /**
     * 判定指定日期和当天时间相比，当天时间是否已经是相对的明天或更往后靠
     * @param date
     * @return
     */
    public static boolean tomorrowOrMore(Date date) {
        if(date == null) {
            return false;
        }
       LocalDateTime t1 = date2LocalDateTime(date);
       LocalDateTime t2 = currLocalDateTime();
       if(t1.getYear() < t2.getYear()) {
           return true;
       } else {
           if(t1.getMonth().compareTo(t2.getMonth()) < 0) {
               return true;
           } else {
               if(t1.getDayOfMonth() < t2.getDayOfMonth() ) {
                   return true;
               }
           }
       }
       return false;
    }

    /**
     * 获取当前时间（日期+时间）
     * @return
     */
    public static Date currDateTime(){
        LocalDateTime curr = LocalDateTime.now();
        return localDateTime2Date(curr);
    }

    public static LocalDateTime currLocalDateTime(){
         return LocalDateTime.now();
    }

    /**
     * 当前日期 2020-10-20
     * 也相当于一个当天的开始 2020年10月20日 0时0分0秒
     * @return
     */
    public static Date currDate(){
        return Date.from(LocalDate.now().atStartOfDay().atZone(defaultZoneId()).toInstant());
    }

    public static ZoneId defaultZoneId(){
        return ZoneId.systemDefault();
    }


    /**
     * Java8风格的时间转换为旧的date
     * @param t
     * @return
     */
    public static Date localDateTime2Date(LocalDateTime t){
        Instant instance = t.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instance);
    }


    public static LocalDateTime date2LocalDateTime(Date t) {
        return t.toInstant().atZone(defaultZoneId()).toLocalDateTime();
    }



    public static void main(String[] args) {
//        System.out.println(currDate());
//        System.out.println(currDateTime());
//        System.out.println(localDateTime2Date(LocalDateTime.now()));
//        System.out.println(LocalDate.now().atStartOfDay());
    }
}

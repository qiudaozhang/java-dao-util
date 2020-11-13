package com.qiudaozhang.util.time;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * @author 邱道长
 * 2020/10/20
 * 日期工具类,本身依赖于Java8，不过所有的方法都返回的是传统的 java.util.Date
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

    /**
     * 当前日期+时间
     * @return
     */
    public static LocalDateTime currLocalDateTime(){
         return LocalDateTime.now();
    }

    /**
     * 当天的开始，没有时间
     * @return
     */
    public static Date todayNoTime(){
        return Date.from(LocalDate.now().atStartOfDay().atZone(defaultZoneId()).toInstant());
    }

    /**
     * 默认的区域id
     * @return
     */
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


    /**
     * 指定日期添加指定单位的时间
     * @param t
     * @param number 多少天 类比
     * @param unit 单位， 年月日时分秒
     * @return
     */
    public static Date addDate(Date t, int number , TimeUnit unit) {
        LocalDateTime localDateTime = date2LocalDateTime(t);
        switch (unit){
            case DAYS:
                localDateTime = localDateTime.plusDays(number);
                break;
            case MINUTES:
                localDateTime = localDateTime.plusMinutes(number);
                break;
            case SECONDS:
                localDateTime = localDateTime.plusSeconds(number);
                break;
            case HOURS:
                localDateTime = localDateTime.plusHours(number);
                break;
            default:
                throw new RuntimeException("暂时未支持该放手");
        }
        return localDateTime2Date(localDateTime);
    }

    /**
     * 当天开始时间
     * @return
     */
    public static Date todayBegin(){
//        LocalDateTime now = LocalDateTime.now();
        return todayNoTime();
    }

    /**
     * 当天的结束时间
     * @return
     */
    public static Date todayEnd(){
        LocalDateTime l = date2LocalDateTime(todayBegin());
        l = l.plusDays(1);
        l = l.plusSeconds(-1);
        return localDateTime2Date(l);
    }

    /**
     * 修改指定时间的时分秒
     * @param t 传入时间
     * @param pattern  12:12:12
     * @return
     */
    public static Date setHourMinuteSeconds(Date t, String pattern){
        String[] split = pattern.split(":");
        if(split.length != 3) {
            throw new RuntimeException("非法时分秒模式");
        }
        LocalDateTime l = date2LocalDateTime(t);
        int hour =Integer.parseInt( split[0]);
        int minute = Integer.parseInt(split[1]);
        int second = Integer.parseInt(split[2]);
        LocalDateTime time = LocalDateTime.of(l.getYear(),l.getMonth(),l.getDayOfMonth(),hour,minute,second);
        return localDateTime2Date(time);
    }



    public static void main(String[] args) {

        System.out.println(setHourMinuteSeconds(new Date(),"12:12:12"));
//        System.out.println(todayEnd());
//        System.out.println(addDate(new Date(),3,TimeUnit.DAYS));
//        System.out.println(currDate());
//        System.out.println(currDateTime());
//        System.out.println(localDateTime2Date(LocalDateTime.now()));
//        System.out.println(LocalDate.now().atStartOfDay());
    }

    public static Date utc2gmt8(String text) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM-dd-yyyy HH:mm:ss a", Locale.ENGLISH);
        Date date = sdf.parse(text);
        LocalDateTime localDateTime = date2LocalDateTime(date);
        LocalDateTime t = localDateTime.plusHours(8);
        return  localDateTime2Date(t);
    }
}

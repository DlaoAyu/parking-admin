package com.laoayu.parking.common.utils;

import com.laoayu.parking.common.vo.TimeVo;

import java.text.ParseException;
import java.util.Date;

/**
 * @author: LaoAyu
 * @date: 2023/04/16
 **/
public class DateUtil {

    public static TimeVo time(Date EntryTime ,Date ExitTime) throws ParseException {
        TimeVo timeVo = new TimeVo();
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
//        String format = df.format(date);
//        Date endDate = df.parse(format);
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff =  ExitTime.getTime() - EntryTime.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        timeVo.setDay(day);
        timeVo.setHour(hour);
        timeVo.setMin(min);
        return timeVo;
    }
}

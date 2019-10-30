package com.shuojie.utils.timeUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeFormateUtil {

    public static Long timeToStamp(String time){
        long timeStamp;
        try {
            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = format.parse(time);
            //日期转时间戳（毫秒）
             timeStamp=date.getTime()/1000;
            return timeStamp;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}

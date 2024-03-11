package Yipykso.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Time {
    public static String getTimeNow(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }
}

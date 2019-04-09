package com.qiusheng.www.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class IdUtil {
    public static Long generateId(){
        int randomid=(int)(Math.random()*100);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String temp = simpleDateFormat.format(new Date())+randomid;
        Long id = Long.parseLong(temp);
        return id;
    }
}

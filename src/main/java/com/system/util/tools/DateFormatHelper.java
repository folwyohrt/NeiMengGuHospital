package com.system.util.tools;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * @Auther: 李景然
 * @Date: 2018/6/13 10:24
 * @Description:
 */
public class DateFormatHelper {

    private static DateFormatHelper dateFormatHelper;
    private static Properties prop = new Properties();

    private DateFormatHelper(){
        readProperties();
    }

    private static DateFormatHelper getSingle(){
        if(dateFormatHelper==null){
            dateFormatHelper=new DateFormatHelper();
        }
        return dateFormatHelper;
    }

    public static String getDateFormatAll() {
        Properties prop = new Properties();
        String filepath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        try {
            prop.load(new FileInputStream(filepath + "webCongfig.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty("dateFormatAll");
    }

    public static String getDateFormat() {
        getSingle();
        return prop.getProperty("dateFormat");
    }

    public static Date getDate(String dateStr) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(getDateFormat());
            Date date = simpleDateFormat.parse(dateStr);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getDateStr(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(getDateFormat());
        String str = simpleDateFormat.format(date);
        return str;
    }

    private void readProperties(){
        String filepath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        // 将文件路径中含有的%20替换为空格，避免出现java.io.fileNotFoundException
        filepath = filepath.replaceAll("%20", " ");
        try {
            prop.load(new FileInputStream(filepath + "webCongfig.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

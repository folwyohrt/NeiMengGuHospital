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
    public static String getDateFormatAll() {
        Properties prop = new Properties();
        String filePath = Thread.currentThread().getContextClassLoader().getResource("").getPath() + "webCongfig.properties";
        try {
            // 将文件路径中含有的%20替换为空格，避免出现java.io.fileNotFoundException
            String encodedPath = filePath.replaceAll("%20", " ");
            prop.load(new FileInputStream(encodedPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty("dateFormatAll");
    }

    public static String getDateFormat() {
        Properties prop = new Properties();
        String filePath = Thread.currentThread().getContextClassLoader().getResource("").getPath() + "webCongfig.properties";
        try {
            // 将文件路径中含有的%20替换为空格，避免出现java.io.fileNotFoundException
            String encodedPath = filePath.replaceAll("%20", " ");
            prop.load(new FileInputStream(encodedPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
}
package com.dzz.medical.common.date;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * 日期处理
 *
 * @author dzz
 * @version 1.0.0
 * @since 2019年11月17 18:48
 */
public class DateConvertTools {

    /**
     * 格式化日期数据到字符型
     * @param date 日期数据毫秒数
     * @return 格式化数据
     */
    public static String formatDate(Long date) {

        if(null == date || 0 == date) {
            return "";
        }

        return LocalDateTime.ofInstant(Instant.ofEpochMilli(date), ZoneId.systemDefault())
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

}

package ir.ac.ut.ie.CA_07_mzFoodDelivery.utils;

import org.apache.commons.lang.StringEscapeUtils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.lang.reflect.Array;
import java.security.spec.KeySpec;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

public class StringUtils {

    public static String quoteWrapper(String str) {
        return "'" + str + "'";
    }

    public static String convertToString(LocalDateTime localDateTime) {
        if (localDateTime == null) return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = localDateTime.format(formatter);
        return formattedDateTime;
    }

    public static LocalDateTime convertToLocalDateTime(String time) {
        if (time == null) return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(time, formatter);
        return dateTime;
    }

    public static String stripTags(String str) {
        String stripStr = str;
        List<String> illegalWords = Arrays.asList("\\<.*?\\>", "'", "\"", "_", "%", "=");
        for (String illegalWord: illegalWords) {
            stripStr = stripStr.replaceAll(illegalWord, "");
        }
        stripStr = StringEscapeUtils.escapeSql(stripStr);
        return stripStr;
    }

    public static boolean hasIllegalChars(String str) {
        return !stripTags(str).equals(str);
    }

    public static String hashString(String str) throws Exception{
        return Integer.toString(str.hashCode());
    }
}

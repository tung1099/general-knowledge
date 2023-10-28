package com.example.common.utils;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtils {

    public static String getIpAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }

    public static boolean isStringValidNum(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String getElementFromResponse(String element, String response) {
        if (response == null || !response.contains(element)) {
            return null;
        }
        String result = response;
        result = result.substring(response.indexOf("<" + element + ">") + ("<" + element + ">").length());
        result = result.substring(0, result.indexOf("</" + element + ">"));
        return result.trim();
    }

    public static String getElementFromResponseInOriginal(String element, String response) {
        String result = getElementFromResponse("original", response);
        return getElementFromResponse(element, result);
    }

    public static boolean checkEmpty(String text) {
        if (text != null && !text.isEmpty()) {
            return false;
        }
        return true;
    }

    /*
     * format text of date 20230417000000 to 17
     * */
    public static String getDate(String text) {
        try {
            Date date = new SimpleDateFormat("yyyyMMddHHmmss").parse(text);
            return new SimpleDateFormat("dd").format(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /*
     * format text: 7 -> 07
     * */
    public static String formatNumber(String number) {
        if (checkEmpty(number)) return "";
        if (Integer.parseInt(number) < 10) {
            return "0" + number;
        }
        return number;
    }

    public static String setWhiteSpaceAtEdge(String str){
        return " " + str.trim() + " ";
    }
}

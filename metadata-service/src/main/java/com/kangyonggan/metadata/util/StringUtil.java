package com.kangyonggan.metadata.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * @author kangyonggan
 * @since 2016/12/10
 */
public class StringUtil {

    /**
     * 正则表达式：验证密码
     */
    public static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{8,20}$";

    /**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0-3,5-9]))\\d{8}$";

    /**
     * 正则表达式：验证邮箱
     */
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    /**
     * 正则表达式：验证汉字
     */
    public static final String REGEX_CHINESE = "^[\u4e00-\u9fa5],{0,}$";

    /**
     * 正则表达式：验证URL
     */
    public static final String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";

    /**
     * <pre>
     * StringUtil.isEmpty(null) = true
     * StringUtil.isEmpty("") = true
     * StringUtil.isEmpty("    ") = true
     * StringUtil.isEmpty("abc") = false
     * </pre>
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    /**
     * To See StringUtil.isEmpty(String str)
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * <pre>
     * StringUtil.hasEmpty(null, "asd", "qwe") = true
     * StringUtil.hasEmpty("", "asd") = true
     * StringUtil.hasEmpty("    ", "asd") = true
     * StringUtil.hasEmpty("abc", "asd") = false
     * </pre>
     *
     * @param arr
     * @return
     */
    public static boolean hasEmpty(String... arr) {
        for (String str : arr) {
            if (isEmpty(str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isEmpty(String... arr) {
        for (String str : arr) {
            if (isNotEmpty(str)) {
                return false;
            }
        }
        return true;
    }

    /**
     * <pre>
     * StringUtil.capitalize(null)  = null
     * StringUtil.capitalize("")    = ""
     * StringUtil.capitalize("cat") = "Cat"
     * StringUtil.capitalize("cAt") = "CAt"
     * </pre>
     *
     * @param str
     * @return
     */
    public static String capitalize(String str) {
        if (isEmpty(str)) {
            return str;
        }

        char firstChar = str.charAt(0);
        if (Character.isTitleCase(firstChar)) {
            return str;
        }

        return new StringBuilder(str.length())
                .append(Character.toTitleCase(firstChar))
                .append(str.substring(1))
                .toString();
    }

    /**
     * 判断str是否在arr中
     *
     * @param str
     * @param arr
     * @return
     */
    public static boolean in(String str, String... arr) {
        for (String s : arr) {
            if (s.equals(str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 驼峰单词转数组
     *
     * @param word
     * @return
     */
    public static String[] camelToArray(String word) {
        if (StringUtils.isEmpty(word)) {
            return null;
        }

        StringBuilder sb = new StringBuilder();

        char chars[] = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            if (Character.isUpperCase(ch)) {
                sb.append("_").append(Character.toLowerCase(ch));
            } else {
                sb.append(ch);
            }
        }

        if (sb.toString().startsWith("_")) {
            sb.deleteCharAt(0);
        }
        if (sb.toString().endsWith("_")) {
            sb.deleteCharAt(sb.lastIndexOf("_"));
        }

        return sb.toString().split("_");
    }

    /**
     * 首字母变大写
     *
     * @param str
     * @return
     */
    public static String firstToUpperCase(String str) {
        if (str == null || str.trim().length() == 0) {
            return str;
        }
        if (str.length() == 1) {
            return str.toUpperCase();
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * 两边都加上%
     *
     * @param str
     * @return
     */
    public static String toLikeString(String str) {
        if (str == null) {
            str = "";
        }
        return String.format("%%%s%%", str);
    }

    /**
     * 判断是否是基本数据类型或String
     *
     * @param clazz
     * @return
     */
    public static boolean isWrapClass(Class clazz) {
        try {
            if ("String".equals(clazz.getSimpleName())) {
                return true;
            }
            return ((Class) clazz.getField("TYPE").get(null)).isPrimitive();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 校验手机号
     *
     * @param mobile
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isMobile(String mobile) {
        return Pattern.matches(REGEX_MOBILE, mobile);
    }

    /**
     * 校验密码
     *
     * @param password
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isPassword(String password) {
        return Pattern.matches(REGEX_PASSWORD, password);
    }

    /**
     * 校验邮箱
     *
     * @param email
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isEmail(String email) {
        return Pattern.matches(REGEX_EMAIL, email);
    }

    /**
     * 校验汉字
     *
     * @param chinese
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isChinese(String chinese) {
        return Pattern.matches(REGEX_CHINESE, chinese);
    }

    /**
     * 校验URL
     *
     * @param url
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isUrl(String url) {
        return Pattern.matches(REGEX_URL, url);
    }

    /**
     * 接收手机端转码
     *
     * @param data
     * @return
     */
    public static String decode(String data) {
        try {
            return new String(data.getBytes("ISO-8859-1"), "UTF-8");
        } catch (Exception e) {
            return data;
        }
    }

    /**
     * 把tableName转为java类名
     *
     * @param tableName
     * @return
     */
    public static String convertTableName(String tableName) {
        boolean isLine = false;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tableName.length(); i++) {
            char ch = tableName.charAt(i);

            // 首字母转大写
            if (i == 0) {
                sb.append(Character.toUpperCase(ch));
            } else {
                // 下划线后面的字母转大写
                if (isLine) {
                    sb.append(Character.toUpperCase(ch));
                } else {
                    sb.append(ch);
                }
            }

            if (ch == '_') {
                sb.deleteCharAt(sb.lastIndexOf("_"));
                isLine = true;
            } else  {
                isLine = false;
            }
        }

        return sb.toString();
    }
}

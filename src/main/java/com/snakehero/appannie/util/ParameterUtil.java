package com.snakehero.appannie.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 参数构造工具类。
 */
public class ParameterUtil {

    /**
     * 将Map组装成待签名数据。 待签名的数据必须按照一定的顺序排列
     * <br />直接追加而不是使用&符号
     * <br />空,null参数不放入签名
     * 
     * @param params
     * @return
     */
    public static String getSignDataInAppend(Map<String, String> params, String[] notIn) {
        // 将Map转变为数组
        List<String> paramList = new ArrayList<String>(params.size());
        for (Entry<String, String> entry : params.entrySet()) {
            // 去掉空的参数
            if (StringUtil.isEmpty(entry.getValue())) {
                continue;
            }
            paramList.add(entry.getKey().toString() + "=" + entry.getValue().toString());
        }
        Collections.sort(paramList);
        StringBuffer content = new StringBuffer();
        for (String param : paramList) {
            content.append(param);
        }

        return content.toString();
    }

    /**
     * 将Map组装成待签名数据。 待签名的数据必须按照一定的顺序排列
     * 
     * @param params
     * @return
     */
    public static String getSignData(Map<String, String> params, String[] notIn) {
        StringBuffer content = new StringBuffer();

        // 按照key做排序
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        List<String> notInKeyList = null;
        if (notIn != null) {
            notInKeyList = Arrays.asList(notIn);
        }
        int index = 0;
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);

            if (notInKeyList != null && notInKeyList.indexOf(key) >= 0) {
                continue;
            }
            String value = params.get(key);
            if (value != null) {
                content.append((index == 0 ? "" : "&") + key + "=" + value);
            } else {
                content.append((index == 0 ? "" : "&") + key + "=");
            }
            index++;
        }

        return content.toString();
    }

    public static String getSignData(Map<String, String> params) {
        return getSignData(params, null);
    }

    /**
     * 将Map中的数据组装成url
     * 
     * @param params
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String mapToUrl(Map<String, String> params, String[] notIn, String charset) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for (String key : params.keySet()) {
            String value = params.get(key);
            // System.out.println(key+":"+value);
            if (notIn != null && Arrays.asList(notIn).indexOf(key) >= 0)
                continue;

            if (isFirst) {
                isFirst = false;
            } else {
                sb.append("&");
            }

            if (value != null) {
                sb.append(key + "=" + (charset == null ? value : URLEncoder.encode(value, charset)));
            } else {
                sb.append("&" + key + "=");
            }
        }
        return sb.toString();
    }

    public static String mapToUrl(Map<String, String> params, String[] notIn) throws UnsupportedEncodingException {
        return mapToUrl(params, notIn, "utf-8");
    }

    public static String mapToUrl(Map<String, String> params) throws UnsupportedEncodingException {
        return mapToUrl(params, null, "utf-8");
    }

    public static String mapToUrl(Map<String, String> params, String charset) throws UnsupportedEncodingException {
        return mapToUrl(params, null, charset);
    }

    /**
     * 取得URL中的参数值。
     * <p>
     * 如不存在，返回空值。
     * </p>
     * 
     * @param url
     * @param name
     * @return
     */
    public static String getParameter(String url, String name) {
        if (name == null || name.equals("")) {
            return null;
        }
        name = name + "=";
        int start = url.indexOf(name);
        if (start < 0) {
            return null;
        }

        if (start > 0) {
            name = "&" + name;
            start = url.indexOf(name);
        }
        start += name.length();
        int end = url.indexOf("&", start);
        if (end == -1) {
            end = url.length();
        }
        return url.substring(start, end);
    }

    public static Map<String, String> urlToMap(String url) {
        return urlToMap(url, "utf-8");
    }

    public static Map<String, String> urlToMap(String url, String charset) {
        Map<String, String> params = new HashMap<String, String>();
        if (!StringUtil.isEmpty(url)) {
            int start = url.indexOf("?");
            if (start != -1) {
                url = url.substring(start);
            }
            String[] keyValues = url.split("&");
            for (String keyValue : keyValues) {
                int index = keyValue.indexOf("=");
                if (index != -1) {
                    String key = keyValue.substring(0, index);
                    String value = keyValue.substring(index + 1);
                    try {
                        params.put(key.trim(), URLDecoder.decode(value.trim(), charset));
                    } catch (UnsupportedEncodingException e) {

                    }
                }
            }
        }
        return params;
    }

    /**
     * 将key-value格式的字符串转为map
     * <br/>一个key只能出现一次,重复出现则取最后的值
     * @param kvStr
     * @param secDivi 内容分隔符
     * @param kvDivi key-value分隔符
     * @param trimValue 是否对key和value进行trim操作
     * @return
     */
    public static Map<String, String> kvStrToMap(String kvStr, String secDivi, String kvDivi, boolean trimValue) {
        Map<String, String> params = new HashMap<String, String>();
        if (!StringUtil.isEmpty(kvStr)) {
            String[] keyValues = kvStr.split(secDivi);
            for (String keyValue : keyValues) {
                int index = keyValue.indexOf(kvDivi);
                if (index != -1) {
                    String key = keyValue.substring(0, index);
                    String value = keyValue.substring(index + 1);
                    if (trimValue) {
                        params.put(key.trim(), value.trim());
                    } else {
                        params.put(key, value);
                    }
                }
            }
        }
        return params;
    }

    /**
     * 将key-value格式的字符串转为map
     * <br/>一个key只能出现一次,重复出现则取最后的值
     * @param kvStr
     * @param secDivi 内容分隔符
     * @param kvDivi key-value分隔符
     * @return
     */
    public static Map<String, String> kvStrToMap(String kvStr, String secDivi, String kvDivi) {
        return kvStrToMap(kvStr, secDivi, kvDivi, true);

    }

    /**
     * 将Map中的数据转为类似url格式的字符串
     * 
     * @param params
     * @param joinSymb
     *            连接字符串
     * @param notIn
     * @return
     */
    public static String joinMap(Map<String, String> params, String joinSymb, String[] notIn) {
        for (String key : notIn) {
            params.remove(key);
        }
        String rStr = params.toString();
        rStr = rStr.replaceAll("[\\s\\{\\}]", "").replaceAll(",", joinSymb);
        return rStr;
    }

    public static String joinMap(Map<String, String> params, String joinSymb) {
        return joinMap(params, joinSymb, new String[] {});
    }

    public static String strAryMap2Url(Map<String, String[]> map) {
        String s = "";
        for (String key : map.keySet()) {
            String[] values = map.get(key);
            if (values.length > 0) {
                for (String value : values) {
                    s += "&" + key + "=" + value;
                }
            } else {
                s += "&" + key + "=";
            }
        }
        return s;
    }

}

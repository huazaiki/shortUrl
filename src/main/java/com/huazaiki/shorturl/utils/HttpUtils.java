package com.huazaiki.shorturl.utils;


import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by huazaiki on 2025/03/21.
 */
public class HttpUtils {

    /**
     * 检查目标 URL 是否支持 https 连接
     * @param url
     * @return
     */
    public static boolean supportsHttps(String url) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL("https://" + url).openConnection();
            connection.setRequestMethod("HEAD");
            connection.setConnectTimeout(3000);
            connection.setReadTimeout(3000);
            int responseCode = connection.getResponseCode();
            return (responseCode >= 200 && responseCode < 400);
        } catch (Exception e) {
            return false;
        }
    }
}

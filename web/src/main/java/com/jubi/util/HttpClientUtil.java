package com.jubi.util;

import com.jubi.RestResult;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Administrator on 2017/8/3.
 */
public class HttpClientUtil {

    private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    public static String get(String serverUrl) {
        StringBuilder responseBuilder = null;
        BufferedReader reader = null;
        OutputStreamWriter wr = null;

        try {
            URL url = new URL(serverUrl);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(false);
            conn.setConnectTimeout(1000 * 3);

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            responseBuilder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                responseBuilder.append(line);
            }
            return responseBuilder.toString();
        } catch (IOException e) {
            logger.error("", e);
        } finally {

            if (wr != null) {
                try {
                    wr.close();
                } catch (IOException e) {
                    logger.error("close error", e);
                }
            }

            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    logger.error("close error", e);
                }
            }

        }

        return null;
    }

}

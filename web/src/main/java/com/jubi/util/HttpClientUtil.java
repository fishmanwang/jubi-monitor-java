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

import java.io.IOException;

/**
 * Created by Administrator on 2017/8/3.
 */
public class HttpClientUtil {

    private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    private static CloseableHttpClient httpclient = HttpClients.createDefault();

    public static String get(String url) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(url);
        CloseableHttpResponse response = null;
        String data = "{}";
        try {
            response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            // response.getStatusLine()
            if (entity != null) {
                data = EntityUtils.toString(entity);
                EntityUtils.consume(entity);
            }
        } catch (IOException e) {
            logger.warn(e.getMessage(), e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {
            }
        }
        return data;
    }

}

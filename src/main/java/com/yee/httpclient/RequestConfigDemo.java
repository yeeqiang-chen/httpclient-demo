package com.yee.httpclient;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Title:
 * Description:
 * Create Time: 2017/1/7 0007 17:00
 *
 * @author: YEEQiang
 * @version: 1.0
 */
public class RequestConfigDemo {
    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://www.baidu.com/");
        RequestConfig config = RequestConfig.custom().setConnectTimeout(1000) // the longest time to create a connection
                .setConnectionRequestTimeout(600) // the longest time to get a connection from the connection pool
                .setSocketTimeout(15*1000)  // the longest time of data transmission
                .setStaleConnectionCheckEnabled(true) // test the connection before submitting the request
                .build();
        httpGet.setConfig(config);

        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                System.out.println(content);
            }
        } finally {
            if (response != null) {
                response.close();
            }
            httpClient.close();
        }
    }
}

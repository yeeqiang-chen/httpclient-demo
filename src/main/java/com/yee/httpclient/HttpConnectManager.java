package com.yee.httpclient;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Title:
 * Description:
 * Create Time: 2017/1/7 0007 16:49
 *
 * @author: YEEQiang
 * @version: 1.0
 */
public class HttpConnectManager {
    public static void main(String[] args) throws IOException {
        PoolingHttpClientConnectionManager clientConnectionManager = new PoolingHttpClientConnectionManager();
        // set maximum number of connections
        clientConnectionManager.setMaxTotal(200);
        // set concurrent number for each host address
        clientConnectionManager.setDefaultMaxPerRoute(50);

        doGet(clientConnectionManager);
        doGet(clientConnectionManager);
    }

    public static void doGet(HttpClientConnectionManager cm) throws IOException {
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();
        HttpGet httpGet = new HttpGet("http://www.baidu.com/");
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
            // Warn:you cannot close httpClient here, and if you close httpClient, the connection pool will be destroyed
//            httpClient.close();
        }
    }
}

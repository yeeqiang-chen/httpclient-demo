package com.yee.httpclient;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Title:
 * Description:
 * Create Time: 2017/1/7 0007 16:10
 *
 * @author: YEEQiang
 * @version: 1.0
 */
public class DoGET {
    public static void main(String[] args) throws IOException {
        // create HttpClient object
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // create request of http GET
        HttpGet httpGet = new HttpGet("http://www.baidu.com/");

        CloseableHttpResponse response = null;
        try {
            // execute request
            response = httpClient.execute(httpGet);
            // judge whether this return status is 200
            if (response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                System.out.println("content ===> " + content);
            }
        } finally {
            if (response != null) {
                response.close();
            }
            httpClient.close();
        }
    }
}

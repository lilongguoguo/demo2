package com.common.district.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by QDHL on 2017/5/4.
 */
public class WebServiceUtil {

    private static final Logger logger = LoggerFactory.getLogger(WebServiceUtil.class);

    public static String getWebServiceData(String url, String soap) {
        logger.info("调用webservice服务 —— url地址为：" + url);
        logger.info("调用webservice服务 —— xml请求体为：" + soap);
        try {
            URL wsUrl = new URL(url);

            HttpURLConnection conn = (HttpURLConnection) wsUrl.openConnection();

            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/soap+xml; charset=utf-8");

            //请求体
//            String soap = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
//                    "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">" +
//                    "<soap12:Body>" +
//                    "<GetCorpCostItem xmlns=\"http://tempuri.org/\">" +
//                    "<costType>int</costType>" +
//                    "<costNameLike>string</costNameLike>" +
//                    "</GetCorpCostItem>" +
//                    " </soap12:Body>" +
//                    "</soap12:Envelope>";

            OutputStream os = conn.getOutputStream();
            os.write(soap.getBytes(Charset.forName("UTF-8")));

            InputStream is = conn.getInputStream();

            byte[] b = new byte[1024];
            int len = 0;
            StringBuffer buffer = new StringBuffer();
            while ((len = is.read(b)) != -1) {
                String ss = new String(b, 0, len, "UTF-8");
                buffer.append(ss);
            }
            is.close();
            os.close();
            conn.disconnect();

            String resultStr = buffer.toString();
            logger.info("调用webservice服务 —— 返回结果为：" + resultStr);
            return resultStr;
        } catch (MalformedURLException e) {
            logger.error("调用webservice服务异常,{}", e.toString(), e);
            e.printStackTrace();
        } catch (IOException e) {
            logger.error("调用webservice服务异常,{}", e.toString(), e);
            e.printStackTrace();
        }
        return null;
    }

}

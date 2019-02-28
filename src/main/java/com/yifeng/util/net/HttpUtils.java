package com.yifeng.util.net;

import com.yifeng.util.Charset;
import com.yifeng.util.common.DateTimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @author: lijun
 * @Date: 2019-1-19 10:18
 */
public class HttpUtils {

    private static final Logger log = LoggerFactory.getLogger(HttpUtils.class);

    private static final String PROTOCOL_HTTPS = "https";

    private static final int DEFAULT_TIMEOUT = 3 * 1000;

    private HttpUtils() {}

    private static final class DefaultTrustManager implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }
        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }

    public static String sendPost(String url, Map<String, String> heads, String body, int timeout) {
        return send(url, HttpMehtod.POST, heads, body, timeout);
    }

    public static String sendGet(String url, Map<String, String> heads, String body, int timeout) {
        return send(url, HttpMehtod.GET, heads, body, timeout);
    }

    public static String sendPostJson(String url, String body) {
        Map<String, String> heads = new HashMap<String, String>();
        heads.put("Content-Type", "application/json; charset=" + Charset.UTF8);
        return send(url, HttpMehtod.POST, heads, body, DEFAULT_TIMEOUT);
    }

    public static String sendPostJson(String url, String body, String charset, int timeout) {
        Map<String, String> heads = new HashMap<String, String>();
        heads.put("Content-Type", "application/json; charset=" + charset);
        return send(url, HttpMehtod.POST, heads, body, timeout);
    }

    public static String sendPostJson(String url, String body, int timeout) {
        Map<String, String> heads = new HashMap<String, String>();
        heads.put("Content-Type", "application/json; charset=" + Charset.UTF8);
        return send(url, HttpMehtod.POST, heads, body, timeout);
    }

    public static String sendPostForm(String url, String body) {
        Map<String, String> heads = new HashMap<String, String>();
        heads.put("Content-Type", "application/x-www-form-urlencoded; charset=" + Charset.UTF8);
        return send(url, HttpMehtod.POST, heads, body, DEFAULT_TIMEOUT);
    }

    public static String sendPostForm(String url, String body, int timeout) {
        Map<String, String> heads = new HashMap<String, String>();
        heads.put("Content-Type", "application/x-www-form-urlencoded; charset=" + Charset.UTF8);
        return send(url, HttpMehtod.POST, heads, body, timeout);
    }

    public static String sendPostForm(String url, String body, String charset, int timeout) {
        Map<String, String> heads = new HashMap<String, String>();
        heads.put("Content-Type", "application/x-www-form-urlencoded; charset=" + charset);
        return send(url, HttpMehtod.POST, heads, body, timeout);
    }

    private static String send(String url, HttpMehtod method, Map<String, String> heads, String body, int timeout) {
        String result = "";
        BufferedReader in = null;
        try {
            URL httpUrl = new URL(url);
            HttpURLConnection httpURLConnection = null;
            if(Protocol.HTTP.name().equalsIgnoreCase(httpUrl.getProtocol())) {
                // http
                httpURLConnection = (HttpURLConnection) httpUrl.openConnection();
            } else if(Protocol.HTTPS.name().equalsIgnoreCase(httpUrl.getProtocol())) {
                // https
                httpURLConnection = getHttpsURLConnection(httpUrl);
            } else {
                throw new RuntimeException("unsupport protocol -> " + httpUrl.getProtocol());
            }

            httpURLConnection.setRequestMethod(method.name());
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setConnectTimeout(timeout);
            httpURLConnection.setReadTimeout(timeout);
            httpURLConnection.setRequestProperty("connection", "Keep-Alive");
            httpURLConnection.setRequestProperty("accept", "*/*");
            httpURLConnection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

            if(heads != null) {
                for (Map.Entry entry : heads.entrySet()) {
                    httpURLConnection.setRequestProperty((String) entry.getKey(), (String) entry.getValue());
                }
            }

            if (body != null && body.trim().length() != 0) {
                byte[] b = body.getBytes(Charset.UTF8);
                httpURLConnection.setRequestProperty("Content-Length", String.valueOf(b.length));
                httpURLConnection.getOutputStream().write(b);
            }

            httpURLConnection.connect();

            in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    private static HttpsURLConnection getHttpsURLConnection(URL url) {
        try {
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, new TrustManager[]{new DefaultTrustManager()}, new SecureRandom());

            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
            httpsURLConnection.setSSLSocketFactory(sslContext.getSocketFactory());
            httpsURLConnection.setHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String s, SSLSession sslSession) {
                    return true;
                }
            });
            return httpsURLConnection;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception {
        String url = "http://127.0.0.1:9090/interactive-marketing-rest/openx/marketing/TaskService/listTeamRanking";

        String body = "{\"taskId\":\"5c1b3bbbde37100001968c55\",\"openId\":\"123\",\"pageNum\":1,\"pageSize\":50}";
        String result = sendPostJson(url, body);
        System.out.println(result);
    }

    enum  Protocol {
        HTTP, HTTPS;
    }

    enum HttpMehtod {
        GET, POST, HEAD, OPTIONS, PUT, DELETE;
    }
}

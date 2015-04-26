package com.snakehero.appannie.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.snakehero.appannie.ddl.type.UserAgent;



/**
 * 访问https接口。
 */
public class HttpsRequest {
	private static Logger logger = LoggerFactory.getLogger(HttpsRequest.class);
    // 默认的HTTPS 端口
    static final int HTTPS_PORT = 443;
    static int CONN_TIME_OUT = 10000; //连接超时上限
    static int READ_TIME_OUT = 10000; //请求超时上限

    /**
     * 自定义的证书管理类。
     */
    private static class TrustAnyTrustManager implements X509TrustManager {

        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[] {};
        }
    }

    private static class TrustAnyHostnameVerifier implements HostnameVerifier {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }

    /**
     * 发送get请求
     * 
     * @param url
     *            网页地址
     * @param paras
     *            附带参数
     * @return
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     * @throws UnknownHostException
     * @throws IOException
     */
    public static String get(String url, String paras,boolean isAjax) throws KeyManagementException, NoSuchAlgorithmException, UnknownHostException, IOException {
    	logger.info("~~~https request~~~~~url:{}, params:{}", url, paras);
    	String result = request(url, paras, false, null, CONN_TIME_OUT, READ_TIME_OUT,isAjax);
    	//logger.info("~~~https response ~~~~ result:{}", result);
        return result;
    }

    /**
     * 发送get请求
     * 
     * @param url
     *            网页地址
     * @param paras
     *            附带参数
     * @param charset 编码
     * @return
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     * @throws UnknownHostException
     * @throws IOException
     */
    public static String get(String url, String paras, String charset,boolean isAjax) throws KeyManagementException, NoSuchAlgorithmException, UnknownHostException, IOException {

        return request(url, paras, false, charset, CONN_TIME_OUT, READ_TIME_OUT,isAjax);
    }

    /**
     * 发送Post请求
     * 
     * @param url
     *            网页地址
     * @param paras
     *            附带参数
     * @return
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     * @throws UnknownHostException
     * @throws IOException
     */
    public static String post(String url, String paras,boolean isAjax) throws KeyManagementException, NoSuchAlgorithmException, UnknownHostException, IOException {
        return request(url, paras, true, null, CONN_TIME_OUT, READ_TIME_OUT,isAjax);
    }

    /**
     * 发送Post请求
     * 
     * @param url
     *            网页地址
     * @param paramMap
     *            附带参数
     * @return
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     * @throws UnknownHostException
     * @throws IOException
     */
    public static String post(String url, Map<String, String> paramMap,boolean isAjax) throws KeyManagementException, NoSuchAlgorithmException, UnknownHostException, IOException {
        String paramStr = ParameterUtil.mapToUrl(paramMap);
        logger.info("https req tid[{}], url[{}], param[{}]", new Object[]{Thread.currentThread().getId(), url, paramStr});
        String result =  request(url, paramStr, true, null, CONN_TIME_OUT, READ_TIME_OUT,isAjax);
        //logger.info("https resp tid[{}], result[{}]", new Object[]{Thread.currentThread().getId(), result});
        return result;
    }

    /**
     * 发送post请求
     * @param url
     * @param paras
     * @param connTimeOut 连接超时时间
     * @param readTimeOut 读取超时间
     * @return
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     * @throws UnknownHostException
     * @throws IOException
     */
    public static String post(String url, Map<String, String> paras, int connTimeOut, int readTimeOut,boolean isAjax) throws KeyManagementException, NoSuchAlgorithmException, UnknownHostException, IOException {
        String paramStr = ParameterUtil.mapToUrl(paras);
        return request(url, paramStr, true, null, connTimeOut, readTimeOut,isAjax);
    }

    /**
     * 通用请求方法,供get和post调用
     * @param url  网页地址
     * @param paras  附带参数
     * @param doPost 是否执行post请求
     * @param charset 编码,可为null
     * @param connectTimeOut 连接超时时间,单位:毫秒
     * @param readTimeOut  读取超时时间,单位:毫秒
     * @return
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     * @throws UnknownHostException
     * @throws IOException
     */
    private static String request(String url, String paras, boolean doPost, String charset, int connectTimeOut, int readTimeOut,boolean isAjax) throws KeyManagementException, NoSuchAlgorithmException,
            UnknownHostException, IOException {

        if (!doPost) {
            String linkSymbol = "?";
            if (url.indexOf(linkSymbol) > -1) {
                linkSymbol = "&";
            }
            if (paras != null) {
                url = url + linkSymbol + paras;
            }
        }
        URL console = new URL(url);
        HttpsURLConnection conn = (HttpsURLConnection) console.openConnection();
        conn.setConnectTimeout(connectTimeOut);
        conn.setRequestProperty("User-Agent", UserAgent.getRandomUserAgent());
        if(isAjax){
        	conn.setRequestProperty("X-Requested-With", "XMLHttpRequest");;
        	
        }
        conn.setReadTimeout(readTimeOut);
        if (doPost) {
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
        } else {
            conn.setRequestMethod("GET");
        }
        SSLContext sc = genSc();
        conn.setSSLSocketFactory(sc.getSocketFactory());
        conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
        conn.connect();
        if (doPost) {
            OutputStream os = conn.getOutputStream(); // 输出流，写数据
            os.write(paras.getBytes());
            os.flush();
            os.close();
        }

        InputStreamReader isReader;
        if (charset != null) {
            isReader = new InputStreamReader(conn.getInputStream(), charset);
        } else {
            isReader = new InputStreamReader(conn.getInputStream());
        }
        BufferedReader in = new BufferedReader(isReader);
        String line;
        StringBuffer sb = new StringBuffer();
        String br = "";
        while ((line = in.readLine()) != null) {
            sb.append(br);
            sb.append(line);
            br = "\n";
        }
        in.close();
        conn.disconnect();
        return sb.toString();

    }

    /**
     * 构建SSLContext,作为连接的基础
     * 
     * @return
     * @throws KeyManagementException
     * @throws NoSuchAlgorithmException
     */
    private static SSLContext genSc() throws KeyManagementException, NoSuchAlgorithmException {
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, new TrustManager[] { new TrustAnyTrustManager() }, new java.security.SecureRandom());
        return sc;
    }

    public static void main(String argv[]) throws Exception {
        System.out.println(HttpsRequest.get("https://uctest2.ucweb.com/upsw/oapi/queryBalance.htm", "id=a",false));
        System.out.println(HttpsRequest.post("https://uctest2.ucweb.com/upsw/oapi/queryBalance.htm", "id=a",false));

    }
}
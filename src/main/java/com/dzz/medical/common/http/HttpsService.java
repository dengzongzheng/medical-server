package com.dzz.medical.common.http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import lombok.extern.slf4j.Slf4j;

/**
 * 核心管理后台启动类
 *
 * @author dzz
 * @version 1.0.0
 * @since 2018年06月30 下午2:47
 */
@Slf4j
@SuppressWarnings("ALL")
public class HttpsService {
	

	/**
	 * 发起HTTPS请求并获取结果
	 * 
	 * @param requestUrl 请求地址
	 * @param method 请求方式(GET, POST)
	 * @param data 提交的数据
	 */
	public static String sendRequest(String requestUrl, String method, String data) {
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象, 并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new BaseTrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			
			sslContext.init(null, tm, new java.security.SecureRandom());
			
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpsUrlConn = (HttpsURLConnection) url.openConnection();
			httpsUrlConn.setSSLSocketFactory(ssf);
            httpsUrlConn.setDoOutput(true);
            httpsUrlConn.setUseCaches(false);
            httpsUrlConn.setDoInput(true);

			// 设置请求方式(GET/POST)
			httpsUrlConn.setRequestMethod(method);

			if ("GET".equalsIgnoreCase(method)){
                httpsUrlConn.connect();
            }

			// 当有数据需要提交时
			if (null != data) {
				OutputStream outputStream = httpsUrlConn.getOutputStream();
				// 注意编码格式, 防止中文乱码
				outputStream.write(data.getBytes(StandardCharsets.UTF_8));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpsUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			
			bufferedReader.close();
			inputStreamReader.close();
			
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpsUrlConn.disconnect();
			
			return buffer.toString();
		} catch (ConnectException ce) {
			log.error("Remote ssl server connection timed out.", ce);
		} catch (Exception e) {
			log.error("Https request error.", e);
		}
		
		return null;
	}
}
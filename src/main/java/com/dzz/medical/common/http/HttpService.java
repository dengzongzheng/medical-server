package com.dzz.medical.common.http;

import com.google.common.base.Strings;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import lombok.extern.slf4j.Slf4j;

/**
 * 核心管理后台启动类
 *
 * @author dzz
 * @version 1.0.0
 * @since 2018年06月30 下午2:47
 */
@Slf4j
public class HttpService {
	

	
	/**
	 * 发起HTTP请求并获取结果
	 * 
	 * @param requestUrl 请求地址
	 * @param requestMethod 请求方式(GET, POST)
	 * @param outputStr 提交的数据
	 * @return 请求结果
	 */
	public static String sendRequest(String requestUrl, String requestMethod, String outputStr) {

		// 方法执行返回值
		StringBuilder buffer = new StringBuilder();
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
			// 设置是否向httpUrlConnection输出, 如果是POST请求, 参数要放在http正文内, 因此需要设为true, 默认情况下是false
			httpUrlConn.setDoOutput(true);
			// 设置是否从httpUrlConnection读入, 默认情况下是true
			httpUrlConn.setDoInput(true);
			// POST请求不能使用缓存
			httpUrlConn.setUseCaches(false);
			// 设置连接主机超时, 30秒
			httpUrlConn.setConnectTimeout(30000);
			// 设置从主机读取数据超时, 30秒
			httpUrlConn.setReadTimeout(30000);
			
			// 设置传送的内容类型是可序列化的Java对象
			// 如果不设此项, 在传送序列化对象时, 当WEB服务默认的不是这种类型时可能抛java.io.EOFException
			httpUrlConn.setRequestProperty("Content-type", "application/x-java-serialized-object");
			
			// 设置请求方式, 默认是GET
			httpUrlConn.setRequestMethod(requestMethod.toUpperCase());

			// 连接, 上面对httpUrlConn的所有配置必须要在connect之前完成
			if ("GET".equalsIgnoreCase(requestMethod)){
                httpUrlConn.connect();
            }

			// 当有数据需要提交时
			if (!Strings.isNullOrEmpty(outputStr)) {
				// 此处getOutputStream会隐含的进行connect(即: 如同调用上面的connect()方法, 所以在开发中不调用上述的connect()也可以)
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式, 防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			
			bufferedReader.close();
			inputStreamReader.close();
			
			// 释放资源
			inputStream.close();

			httpUrlConn.disconnect();
			
			return buffer.toString();
		} catch (ConnectException ce) {
			log.error("Remote server connection timed out. ");
		} catch (Exception e) {
			log.error("Http request error: {}", e);
		}
		return null;
	}
}
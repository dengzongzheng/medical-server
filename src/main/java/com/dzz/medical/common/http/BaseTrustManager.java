package com.dzz.medical.common.http;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

/**
 * 证书信任管理器(用于HTTPS请求)
 * 
 * @author Shangxp
 * @version 1.0.0
 * @date 2015.01.17
 */
public class BaseTrustManager implements X509TrustManager {

	@Override
	public void checkClientTrusted(X509Certificate[] chain, String authType)
		throws CertificateException {
		// TODO Auto-generated method stub
	}

	@Override
	public void checkServerTrusted(X509Certificate[] chain, String authType)
		throws CertificateException {
		// TODO Auto-generated method stub
	}

	@Override
	public X509Certificate[] getAcceptedIssuers() {
		return null;
	}

}
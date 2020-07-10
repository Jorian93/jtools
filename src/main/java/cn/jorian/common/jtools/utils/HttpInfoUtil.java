package cn.com.ncsi.pap.common.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import cn.hutool.http.useragent.Browser;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentParser;

/**
 * 
 * description http info tool
 *
 * @author juyan.ye
 * @date Jul 3, 2020 : 9:13:42 AM
 *
 */
public class ToolHttpInfo {

	private static final String UNKNOWN = "unknown";
	private static final String HOST_1 = "0.0.0.0";
	private static final String HOST_2 = "0:0:0:0:0:0:0:1";
	private static final String HOST_3 = "localhost";
	private static final String HOST_4 = "127.0.0.1";

	/**
	 *获取客户端的 IP
	 * 
	 * @param request
	 * @return
	 */
	public static String getClientIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Real-IP");
		if (ip == null || "".equals(ip.trim()) || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Forwarded-For");
		}
		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (HOST_1.equals(ip) || HOST_2.equals(ip) || HOST_3.equals(ip) || HOST_4.equals(ip)) {
			ip = "127.0.0.1";
		}
		return ip;
	}

	/**
	 * 获取当前机器的IP
	 *
	 * @return /
	 */
	public static String getLocalIp() {
		InetAddress addr;
		try {
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			return "unknown";
		}
		byte[] ipAddr = addr.getAddress();
		StringBuilder ipAddrStr = new StringBuilder();
		for (int i = 0; i < ipAddr.length; i++) {
			if (i > 0) {
				ipAddrStr.append(".");
			}
			ipAddrStr.append(ipAddr[i] & 0xFF);
		}
		return ipAddrStr.toString();
	}

	/**
	 * detect request if Ajax
	 * 
	 * @param request
	 * @return
	 */
	public static boolean ajax(HttpServletRequest request) {
		String accept = request.getHeader("accept");
		return accept != null && accept.contains("application/json") || (request.getHeader("X-Requested-With") != null
				&& request.getHeader("X-Requested-With").contains("XMLHttpRequest"));
	}

	/**
	 * 获得浏览器信息
	 * 
	 * @param request
	 * @return
	 */
	public static String getBrowser(HttpServletRequest request) {
		UserAgent userAgent = UserAgentParser.parse(request.getHeader("User-Agent"));
		Browser browser = userAgent.getBrowser();
		return browser.getName();
	}

}

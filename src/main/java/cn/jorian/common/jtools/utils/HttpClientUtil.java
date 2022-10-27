package cn.jorian.common.jtools.utils;

import cn.jorian.common.jtools.utils.enums.RequestMethodEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * 
 * description http client
 *
 * @author juyan.ye
 * @date Jul 3, 2020 : 9:13:16 AM
 *
 */
@Slf4j
public class ToolHttpClient {

	/**
	 *
	 * @param url
	 * @param parmas 请求参数
	 * @param method 1.GET,get请求 2.POST, post请求
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String send(String url, Map<String, String> parmas, String method) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		String result = "Request Not Execute!！";

		if (RequestMethodEnum.GET.equals(method)) {
			result = this.doGet(url, parmas, entity);
		}
		if (RequestMethodEnum.POST.equals(method)) {
			result = this.doPost(url, parmas, entity);
		}
		return result;
	}

	/**
	 * get请求
	 * 
	 * @param url
	 * @param params
	 * @param entity
	 * @return
	 */
	String doGet(String url, Map<String, String> params, HttpEntity<String> entity) {
		RestTemplate restTemplate = new RestTemplate();
		StringBuilder sb = new StringBuilder();
		params.forEach((k, v) -> {
			sb.append(k);
			sb.append("=");
			sb.append(v);
			sb.append("&");
		});
		url = url + "?" + sb.toString();
		log.info("[HTTP/GET]：url={}" + url);
		String strbody = restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();
		return strbody;
	}

	/**
	 * post请求
	 * 
	 * @param url
	 * @param params
	 * @param entity
	 * @return
	 */
	String doPost(String url, Map<String, String> params, HttpEntity<String> entity) {
		RestTemplate restTemplate = new RestTemplate();
		log.info("[HTTP/POST]：url={}" + url);
		String strbody = restTemplate.exchange(url, HttpMethod.POST, entity, String.class).getBody();
		return strbody;
	}

}

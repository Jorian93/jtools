package cn.jorian.common.jtools.response;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 
 * description 响应工具
 *
 * @author juyan.ye
 * @date Jul 2, 2020 : 11:40:35 PM
 *
 */
public class ResponseTool {

	public static void write(HttpServletResponse response, Integer code, String msg) {
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		try {
			response.getWriter().write(JSON.toJSONString(ResponseResult.builder().code(code).msg(msg).build()));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void write(HttpServletResponse response, ResponseCode responseCode) {
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		try {
			response.getWriter().write(
					JSON.toJSONString(ResponseResult.builder().code(responseCode.code).msg(responseCode.msg).build()));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}

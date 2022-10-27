package cn.jorian.common.jtools.cache;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 *  
 * description
 *
 * @author juyan.ye
 * @date Jul 2, 2020 : 11:09:48 PM
 *
 */
@Data
@Builder
public class JCache implements Serializable {
	/**
	 * <b>Fields</b> serialVersionUID :
	 */
	private static final long serialVersionUID = 7449985759453960984L;
 
	/**
	 * <b>Fields</b> key : 唯一键
	 */
	private String key;
	
	/**
	 *  value : 值
	 */
	private String value;
	
	/**
	 * <b>Fields</b> timestamp : 缓存的时候存的时间戳，用来计算该元素是否过期
	 */
	private Long timestamp;
	
	/**
	 * <b>Fields</b> expire : 有效期 秒 0为无限
	 */
	private Long expire;
	
	/**
	 * <p><b>Title:</b> remainTime</p>
	 * <p><b>Description:</b> 获取剩余时间（秒）</p>
	 * @author juyan.ye
	 * @return
	 */
	public Long remainTime() {
		if (this.expire == 0) {
			return this.expire;
		}
		return this.expire - getTime();
	}
	
	/**
	 * 获取当前时间和元素的相差时间
	 * @return
	 */
	/**
	 * <p><b>Title:</b> getTime</p>
	 * <p><b>Description:</b> 获取当前时间和缓存时间戳差值（秒）+1s </p>
	 * @author juyan.ye
	 * @return
	 */
	private Long getTime() {
		if (this.expire == 0) {
			return this.expire;
		}
		Long current = System.currentTimeMillis();
		Long value = current - this.timestamp;
		return (value / 1000) + 1;
	}
 
	/**
	 * <p><b>Title:</b> isExpire</p>
	 * <p><b>Description:</b> 判断缓存是否到期 false：不到期 true：到期</p>
	 * @author juyan.ye
	 * @return
	 */
	public boolean isExpire() {
		if (this.expire == 0) {
			return false;
		}
		if (getTime() > this.expire) {
			return true;
		}
		return false;
	}
}

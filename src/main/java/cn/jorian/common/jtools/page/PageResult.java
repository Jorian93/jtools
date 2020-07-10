package cn.com.ncsi.pap.common.entity;

import java.util.List;

import lombok.Builder;
import lombok.Data;

/**
 * 分页查询返回结果
 * @author juyan.ye
 * @date 2020-7-1 18:29:00
 */
@Data
@Builder
public class PageResult {
	/**
	 * records 分页数据
	 */
	List<?> records;
	
	/**
	 * total 数据条目
	 */
	int total;
}

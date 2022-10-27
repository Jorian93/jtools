package cn.jorian.common.jtools.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * page查询
 * @author juyan.ye
 * @date 2020-7-1 18:29:00
 */
@Data
@Accessors(chain = true)
public abstract class PageDTO {

	@ApiModelProperty(value = "currentPage", name = "page", example = "1")
	private Integer page = 1;

	@ApiModelProperty(value = "pagesize", name = "limit", example = "20")
	private Integer limit = 10;

}

package cn.com.ncsi.pap.common.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;


/**
 * 
 * description 响应体
 *
 * @author juyan.ye
 * @date Jul 2, 2020 : 11:18:20 PM
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "系统响应")
@Getter
@Setter
public class ResponseResult implements Serializable {


	private static final long serialVersionUID = -7225971282350134261L;
	
	@ApiModelProperty(value = "响应码")
    private  Integer code;
    @ApiModelProperty(value = "响应消息")
    private  String msg;
    @ApiModelProperty(value = "响应数据")
    private  Object data;

    /****
     * 实例初始化的方式构造返回结果
     * @param responseCode
     */
    public ResponseResult(ResponseCode responseCode){
        this.code = responseCode.code;
        this.msg = responseCode.msg;
    }
    public ResponseResult(ResponseCode responseCode, Object data){
        this.code = responseCode.code;
        this.msg = responseCode.msg;
        this.data = data;
    }
    /****
     * 操作方法的方式返回
     * @param responseCode
     */
    public static ResponseResult e(ResponseCode responseCode){
       return e(responseCode,null);
    }
    public static ResponseResult e(HttpStatus httpstatus){
        return e(httpstatus,null);
     }
    public static ResponseResult e(ResponseCode responseCode, Object data){
        return ResponseResult.builder()
                .code(responseCode.code)
                .msg(responseCode.msg)
                .data(data)
                .build();
    }
    public static ResponseResult e(HttpStatus httpstatus, Object data){
        return ResponseResult.builder()
                .code(httpstatus.value())
                .msg(httpstatus.getReasonPhrase())
                .data(data)
                .build();
    }


}

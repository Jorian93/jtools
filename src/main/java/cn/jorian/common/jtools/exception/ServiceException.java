package cn.jorian.common.jtools.exception;

import cn.jorian.common.jtools.response.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 
 * description 服务异常
 *
 * @author juyan.ye
 * @date Jul 2, 2020 : 11:16:05 PM
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceException extends RuntimeException implements Serializable{
     private  String message;
     private  Integer status;
     private Exception e;

    public ServiceException(String message){
        super(message);
    }
    public ServiceException(String message, Throwable e){
        super(message,e);
    }
    public ServiceException(String message, Integer status){
        this.message = message;
        this.status = status;
    }
    public ServiceException(Throwable e){
        super(e);
    }

    public ServiceException(ResponseCode responseCode){
        this.message = responseCode.msg;
        this.status = responseCode.code;
    }

    public ServiceException(String message, Throwable e, boolean enableSuppression, boolean writableStackTrace){
        super(message,e,enableSuppression,writableStackTrace);
    }



}

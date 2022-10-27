
package cn.jorian.common.jtools.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * 
 * description
 *
 * @author juyan.ye
 * @date Jul 2, 2020 : 11:14:37 PM
 *
 */
@Getter
public class BadRequestException extends RuntimeException{

    private static final long serialVersionUID = -1970541539796206193L;
    private Integer status = BAD_REQUEST.value();

    public BadRequestException(String msg){
        super(msg);
    }

    public BadRequestException(HttpStatus status,String msg){
        super(msg);
        this.status = status.value();
    }
}

package cn.com.ncsi.pap.common.utils;

import cn.com.ncsi.pap.common.exception.BadRequestException;

/**
 * 
 * description validate
 *
 * @author juyan.ye
 * @date Jul 3, 2020 : 9:14:50 AM
 *
 */
public class ValidationUtils {
    
    public static void isNull(Object o){
        if(StringUtils.isEmpty(o.toString())){
            String msg =o.toString()+"为空";
            throw new BadRequestException(msg);
        }
    }


}
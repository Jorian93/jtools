package cn.jorian.common.jtools.utils;

import cn.jorian.common.jtools.exception.BadRequestException;

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
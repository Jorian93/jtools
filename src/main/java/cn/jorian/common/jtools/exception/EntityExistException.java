
package cn.jorian.common.jtools.exception;

import org.springframework.util.StringUtils;

/**
 * 
 * description
 *
 * @author juyan.ye
 * @date Jul 2, 2020 : 11:15:23 PM
 *
 */
public class EntityExistException extends RuntimeException {


    private static final long serialVersionUID = -529757616810038783L;

    public EntityExistException(Class<?> clazz, String field, String val) {
        super(EntityExistException.generateMessage(clazz.getSimpleName(), field, val));
    }

    private static String generateMessage(String entity, String field, String val) {
        return StringUtils.capitalize(entity)
                + " with " + field + " "+ val + " existed";
    }
}

package cn.jorian.common.jtools.exception;

import org.springframework.util.StringUtils;

/**
 * 
 * description
 *
 * @author juyan.ye
 * @date Jul 2, 2020 : 11:15:31 PM
 *
 */
public class EntityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -8112803095240189447L;

	public EntityNotFoundException(Class clazz, String field, String val) {
        super(EntityNotFoundException.generateMessage(clazz.getSimpleName(), field, val));
    }

    private static String generateMessage(String entity, String field, String val) {
        return StringUtils.capitalize(entity)
                + " with " + field + " "+ val + " does not exist";
    }
}
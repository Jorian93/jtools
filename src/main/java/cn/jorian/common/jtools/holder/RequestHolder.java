
package cn.com.ncsi.pap.common.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 
 * description 获取servlet
 *
 * @author juyan.ye
 * @date Jul 2, 2020 : 11:40:01 PM
 *
 */
public class RequestHolder {

    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }
}

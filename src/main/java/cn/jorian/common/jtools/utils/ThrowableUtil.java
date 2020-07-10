
package cn.com.ncsi.pap.common.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 
 * description 异常处理工具类
 *
 * @author juyan.ye
 * @date Jul 2, 2020 : 11:23:46 PM
 *
 */
public class ThrowableUtil {

    /**
     * 获取堆栈信息
     */
    public static String getStackTrace(Throwable throwable){
        StringWriter sw = new StringWriter();
        try (PrintWriter pw = new PrintWriter(sw)) {
            throwable.printStackTrace(pw);
            return sw.toString();
        }
    }
}

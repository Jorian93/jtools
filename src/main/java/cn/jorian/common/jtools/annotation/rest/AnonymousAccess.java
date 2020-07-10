
package cn.jorian.common.jtools.annotation.rest;

import java.lang.annotation.*;

/**
 * 用于标记匿名方法
 * @author juyan.ye
 * @date Jul 2, 2020 : 11:03:19 PM
 *
 */
@Inherited
@Documented
@Target({ElementType.METHOD,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AnonymousAccess {

}

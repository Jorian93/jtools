package cn.jorian.common.jtools.utils;

import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * 
 * description spring tools
 *
 * @author juyan.ye
 * @date Jul 3, 2020 : 9:14:30 AM
 *
 */
public class ToolSpring {

	@Autowired
	private static ApplicationContext applicationContext;

	/**
	 * Get bean name
	 * 
	 * @return
	 */
	public static List<String> getBeanNames() {
		String[] strings = applicationContext.getBeanDefinitionNames();
		List<String> list = new ArrayList<>();
		for (String str : strings) {
			if (str.contains(".")) {
				continue;
			}
			Class<?> clazz = getClass(str);
			if (clazz.isAnnotationPresent(Service.class) && str.toLowerCase().contains("service")) {
				list.add(str);
			}
		}
		// sort!
		Collections.sort(list);
		return list;
	}

	/**
	 * Get bean all methods
	 * 
	 * @param name
	 * @return
	 */
	public static Set<String> getBeansMethods(String name) {
		Class<?> clazz = getClass(name);
		Method[] methods = clazz.getDeclaredMethods();
		Set<String> names = new HashSet<>();
		Arrays.asList(methods).forEach(m -> {
			int b = m.getModifiers();// public 1 static 8 final 16
			if (Modifier.isPublic(b)) {
				Class<?>[] classes = m.getParameterTypes();
				if (classes.length == 0) {
					names.add(m.getName());
				}
			}
		});
		return names;
	}

	/**
	 * Get class by give name
	 * 
	 * @param name
	 * @return
	 */
	private static Class<?> getClass(String name) {
		Object object = applicationContext.getBean(name);
		Class<?> clazz = object.getClass();
		if (AopUtils.isAopProxy(object)) {
			clazz = clazz.getSuperclass();
		}
		return clazz;
	}

}

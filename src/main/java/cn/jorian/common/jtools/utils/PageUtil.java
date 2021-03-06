
package cn.com.ncsi.pap.common.utils;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.data.domain.Page;

import cn.com.ncsi.pap.common.entity.PageResult;

import java.util.*;

/**
 * 
 * description 分页工具
 *
 * @author juyan.ye
 * @date Jul 2, 2020 : 11:27:55 PM
 *
 */
public class PageUtil extends cn.hutool.core.util.PageUtil {

    /**
     * List 分页
     */
    public static List toPage(int page, int size , List list) {
        int fromIndex = page * size;
        int toIndex = page * size + size;
        if(fromIndex > list.size()){
            return new ArrayList();
        } else if(toIndex >= list.size()) {
            return list.subList(fromIndex,list.size());
        } else {
            return list.subList(fromIndex,toIndex);
        }
    }

    /**
     * Page 数据处理，预防redis反序列化报错
     */
    public static Map<String,Object> toPage(Page page) {
        Map<String,Object> map = new LinkedHashMap<>(2);
        map.put("content",page.getContent());
        map.put("totalElements",page.getTotalElements());
        return map;
    }

    /**
     * 自定义分页1
     */
    public static Map<String,Object> toPage(Object object, Object totalElements) {
        Map<String,Object> map = new LinkedHashMap<>(2);
        map.put("content",object);
        map.put("totalElements",totalElements);
        return map;
    }
    
    /**
     * 自定义分页2
     */
    public static PageResult toPageResult(Page<T> page) {
    	List<T> list = page.getContent();
    	Integer total = (int)page.getTotalElements();
        return PageResult.builder().records(list).total(total).build();
    }

}

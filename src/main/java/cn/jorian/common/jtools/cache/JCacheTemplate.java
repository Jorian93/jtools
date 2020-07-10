package cn.com.ncsi.pap.common.cache;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


/**
 * 
 * description 内存级缓存类,自带压缩功能、自动清除过期时间功能
 *
 * @author juyan.ye
 * @date Jul 2, 2020 : 11:10:08 PM
 *
 */
@Slf4j
@Component
public class JCacheTemplate {
    /** 饿汉式单例 */
    // private static final Map<String, JCache> cache = new
    // ConcurrentHashMap<String, JCache>();
    // private static final Map<String, JCache> cache = new
    // ConcurrentSkipListMap<String, JCache>();
    /**
     * Initialization Demand Holder (IoDH)
     * 技术，单例类中增加一个静态(static)内部类，既可以实现延迟加载，又可以保证线程安全，不影响系统性能
     */
    private static class HolderClass {
        // 跳表实现 类似redis数据结构，空间换时间加快速度
        private static final Map<String, JCache> J_CACHE = new ConcurrentSkipListMap<String, JCache>();
    }

    private JCacheTemplate() {
    }

    /**
     * 清理过期缓存是否在运行
     */
    public static volatile Boolean CLEAN_THREAD_IS_RUN = false;

    /**
     * 清理缓存间隔时间以及默认过期时间60秒
     */
    static final Long DEFALUT_EXPIRE = 60L;

    /**
     * 是否压缩
     */
    public static volatile Boolean IS_ZIP = true;

    /**
     * <b>Description:</b> 清空全部缓存，项目启动时使用或实际业务中不建议使用
     * </p>
     * 
     * @author juyan.ye
     */
    public static void clearAll() {
        HolderClass.J_CACHE.clear();
    }

    /**
     * <p>
     * <b>Description:</b> 获取所有数据
     * </p>
     * 
     * @author juyan.ye
     * @return
     */
    public static Map<String, JCache> getAll() {
        return HolderClass.J_CACHE;
    }

    /**
     * <p>
     * <b>Description:</b> 获取缓存大小
     * </p>
     * 
     * @author juyan.ye
     * @return
     */
    public static int size() {
        return HolderClass.J_CACHE.size();
    }

    /**
     * <p>
     * <b>Description:</b> 获取对象
     * </p>
     * 
     * @author juyan.ye
     * @param key
     *                键
     * @return
     */
    public static String get(String key) {
        if (checkCache(key)) {
            JCache entity = HolderClass.J_CACHE.get(key);
            if (IS_ZIP) {
                return GZIPUtils.uncompress(entity.getValue());
            }
            return entity.getValue();
        }
        return null;
    }

    /**
     * <p>
     * <b>Description:</b> 判断缓存在不在,过没过期
     * </p>
     * 
     * @author juyan.ye
     * @param key
     * @return
     */
    public static boolean checkCache(String key) {
        JCache entity = HolderClass.J_CACHE.get(key);
        if (entity == null) {
            return false;
        }
        if (entity.isExpire()) {
            return false;
        }
        return true;
    }

    public static void resetExpired(String key, Long newExpired) {
        JCache entity = HolderClass.J_CACHE.get(key);
        if (entity == null) {      
            throw new IllegalArgumentException("none token has key: "+key);         
        }
        entity.setExpire(newExpired);
    }

    /**
     * 查询key的过期时间总长
     * @param key
     * @return
     */
    public static Long getExpiredTime(String key){
        JCache entity = HolderClass.J_CACHE.get(key);
        if (entity == null) {
            return null;
        }
        return  entity.getExpire();     
    }
    /**
     * 查询key的剩余时间
     * @param key
     * @return
     */
    public static Long getExpired(String key){
        JCache entity = HolderClass.J_CACHE.get(key);
        if (entity == null) {
            return null;
        }
        Long expiredTime = entity.getExpire();
        Long currTime = System.currentTimeMillis()/1000;
        return currTime-expiredTime;
    }
    /**
     * <p><b>Description:</b> 清除对应的对象</p>
     * @author juyan.ye
     * @param key 键
     */
	public static void removeCache(String key){
		log.info("remove key:" + key);
		HolderClass.J_CACHE.remove(key);
	}
	
	/**
	 * <p><b>Description:</b> 放置缓存</p>
	 * @author juyan.ye
	 * @param key 键
	 * @param json 值
	 */
	public static void put(String key, String json) {
		put(key, json, DEFALUT_EXPIRE - 1);
	}
	
	/**
	 * <p><b>Description:</b> 放置缓存</p>
	 * @author juyan.ye
	 * @param key 键
	 * @param json 值
	 * @param expire 过期时间（秒） 0为永久
	 */
	public static void put(String key, String json, Long expire) {
		if (IS_ZIP) {
			json = GZIPUtils.compress(json);
    	        }
        JCache entity = JCache.builder()	     
            .key(key)
            .value(json)
            .timestamp(System.currentTimeMillis())
            .expire(expire)
            .build();
		HolderClass.J_CACHE.put(key, entity);
		startCleanThread();
		log.info("Put json to cache key:" + key + " expire:" + expire + " size: " + json.length());
	}
	
	/**
     * 定时执行
	 * <p><b>Description:</b> 清除所有的过期对象</p>
	 * @author juyan.ye
	 */
	public static void clearExpireEntity() {
        List<String> deleteKeyList = new LinkedList<>();
        for(Map.Entry<String, JCache> entry : HolderClass.J_CACHE.entrySet()) {
            if (entry.getValue().isExpire()) {
            	deleteKeyList.add(entry.getKey());
            }
        }
        
        for (String deleteKey : deleteKeyList) {
        	removeCache(deleteKey);
		}
		log.debug("Delete cache count is :" + deleteKeyList.size());
	}
	
	/**
	 * <p><b>Description:</b> 开启清理过期缓存的线程</p>
	 * @author juyan.ye
	 */
    private static void startCleanThread() {
        if (!CLEAN_THREAD_IS_RUN) {
	        JCacheExpiredCleanThread cleanTimeOutThread = new JCacheExpiredCleanThread();
	        Thread thread = new Thread(cleanTimeOutThread, "j-cache-clean-service");
	        /** 设置为后台守护线程 */
	        thread.setDaemon(true);
	        thread.start();
        }
    }
}
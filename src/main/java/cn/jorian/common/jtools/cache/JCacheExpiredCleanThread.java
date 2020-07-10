package cn.com.ncsi.pap.common.cache;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * description
 *
 * @author juyan.ye
 * @date Jul 2, 2020 : 11:09:56 PM
 *
 */
@Slf4j
public class JCacheExpiredCleanThread implements Runnable {
 
	@Override
	public void run() {
		log.info("===MemoryCache clean thread run===");
		JCacheTemplate.CLEAN_THREAD_IS_RUN = true;
		while(true) {
			try {
                Thread.sleep(JCacheTemplate.DEFALUT_EXPIRE * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                log.error("===MemoryCache clean thread down===");
                JCacheTemplate.CLEAN_THREAD_IS_RUN = false;
                Thread.currentThread().interrupt();
            }
			JCacheTemplate.clearExpireEntity();
	    }
	}
 
}

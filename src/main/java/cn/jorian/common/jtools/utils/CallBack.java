

package cn.jorian.common.jtools.utils;

/**
 * 
 * description 针对需要提交回调方法的初始化
 *
 * @author juyan.ye
 * @date Jul 2, 2020 : 11:21:23 PM
 *
 */
public interface CallBack {
    /**
     * 回调执行方法
     */
    void executor();

    /**
     * 回调任务名称
     * @return /
     */
    default String getCallBackName() {
        return Thread.currentThread().getId() + ":" + this.getClass().getName();
    }
}


package cn.com.ncsi.pap.common.utils;

import org.springframework.context.ConfigurableApplicationContext;


import org.springframework.core.env.Environment;
/**
 * 
 * app运行成功后的提示信息功能
 *
 * @author juyan.ye
 * @date Jul 2, 2020 : 11:19:40 PM
 *
 */
public class AppInfoUtils {

    /**
     * 实例化一个SpringContextHolder
     * @return
     */
    @Bean
    public SpringContextHolder springContextHolder() {
        return new SpringContextHolder();
    }
    /**
     * 打印app提示信息
     * @param application
     */
    public static void printTips(ConfigurableApplicationContext application) {

        Environment env = application.getEnvironment();

        String ip = ToolHttpInfo.getLocalIp();

        String ssl = env.getProperty("server.ssl.key-store");
        String protocol = "http";
        if (ssl != null) {
            protocol = "https";
        }
        String port = env.getProperty("server.port");
        System.out.println("\n----------------------------------------------------------\n\t"
                + "Application  started ! Access URLs:\n\t" + "Local: \t" + protocol + "://localhost:" + port + "/\n\t"
                + "External: " + protocol + "://" + ip + ":" + port + "/\n\t" + "swagger-ui: " + protocol + "://" + ip
                + ":" + port + "/swagger-ui.html\n" + "----------------------------------------------------------");
    }

}
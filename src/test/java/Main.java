import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import org.w3c.dom.css.CSSImportRule;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.Executor;


public class Main {
    public static void main(String[] args) throws NacosException, InterruptedException {
        try {
            System.out.println("Hello world!");
            String serverAddr = "192.168.10.128:8848";
            String dataId = "test";
            String group = "DEFAULT_GROUP";
            Properties properties = new Properties();
            properties.put("serverAddr", serverAddr);
            properties.put("username", "nacos");
            properties.put("password", "nacos");
            ConfigService configService = NacosFactory.createConfigService(properties);
            String content = configService.getConfig(dataId, group, 5000);
            System.out.println(content);

            configService.addListener(dataId, String.valueOf(group), new Listener() {
                @Override
                public void receiveConfigInfo(String configInfo) {
                    System.out.println("receive:" + configInfo);
                }

                @Override
                public Executor getExecutor() {
                    return null;
                }
            });

            System.in.read();
        } catch (NacosException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//
//        boolean isPublishOk = configService.publishConfig(dataId, group, "content");
//        System.out.println(isPublishOk);
//
//        Thread.sleep(3000);
//        content = configService.getConfig(dataId, group, 5000);
//        System.out.println(content);
//
//        boolean isRemoveOk = configService.removeConfig(dataId, group);
//        System.out.println(isRemoveOk);
//        Thread.sleep(3000);
//
//        content = configService.getConfig(dataId, group, 5000);
//        System.out.println(content);
//        Thread.sleep(300000);


    }
}
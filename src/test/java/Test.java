import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Test {
    public static void main(String[] args) throws NacosException, IOException {
        // 创建包含用户名和密码的 Properties 对象
        Properties properties = new Properties();
        properties.put("serverAddr", "192.168.10.128:8848");
        properties.put("username", "nacos");
        properties.put("password", "nacos");
        NamingService namingService = NamingFactory.createNamingService(properties);
        // 若没有身份验证
        // NamingService namingService = NamingFactory.createNamingService("192.168.10.128:8848");
        namingService.registerInstance("user", "1.1.1.1", 8888, "bj");
        //注册不同集群
        NamingService namingService2 = NamingFactory.createNamingService(properties);
        namingService2.registerInstance("user", "2.2.2.2", 8888,"bj");
        //注册不同集群
        NamingService namingService3 = NamingFactory.createNamingService(properties);
        namingService3.registerInstance("user", "3.3.3.3", 8888,"sh");

        NamingService namingService4 = NamingFactory.createNamingService(properties);
        Instance instance = new Instance();
        instance.setIp("4.4.4.4");
        instance.setPort(9999);
        instance.setHealthy(true);
        instance.setWeight(2.0);
        Map<String, String> instanceMeta = new HashMap<String, String>();
        instanceMeta.put("site", "et2");
        instance.setMetadata(instanceMeta);
        namingService4.registerInstance("pass", instance);

        System.in.read();
    }
}

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;

import java.util.Properties;

public class Test1 {
    public static void main(String[] args) throws NacosException {
        // 创建包含用户名和密码的 Properties 对象
        Properties properties = new Properties();
        properties.put("serverAddr", "192.168.10.128:8848");
        properties.put("username", "nacos");
        properties.put("password", "nacos");
        NamingService namingService = NamingFactory.createNamingService(properties);
        //随机拿取一个健康的实例
        //System.out.println(namingService.selectOneHealthyInstance("user"));
        for (Instance instance : namingService.selectInstances("user", true)) {
            System.out.println(instance);
        }

    }
}

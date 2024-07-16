package com.gaomu;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class UserController {
    @Value("${spring.datasource.username}")
    //@NacosValue(value = "${spring.datasource.username}", autoRefreshed = true)
    private String username;
    @GetMapping("/id")
    public String test(){
        return username;
    }
}

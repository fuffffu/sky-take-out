package com.sky.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "sky.redis")
@Data
public class RedisProperties {

    /**
     * 管理端生成redis相关配置
     */
    private String host;
    private int port;
    private int password;

}

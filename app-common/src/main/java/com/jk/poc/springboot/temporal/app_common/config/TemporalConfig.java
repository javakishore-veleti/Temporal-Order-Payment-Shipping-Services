package com.jk.poc.springboot.temporal.app_common.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TemporalConfig {

    @Value("${temporal.server_host_ip:localhost:7233}")
    private String temporalServerHostAndIp = "localhost:7233";

    private static String temporalServerHostAndIpVal = "localhost:7233";

    @PostConstruct
    public void init() {
        temporalServerHostAndIpVal= temporalServerHostAndIp;
    }

    public static String getTemporalServerHostAndIp() {
        return temporalServerHostAndIpVal;
    }
}

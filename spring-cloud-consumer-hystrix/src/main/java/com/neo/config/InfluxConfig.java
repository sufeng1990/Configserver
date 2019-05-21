package com.neo.config;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by 苏峰 on 2019/5/17.
 */
@Configuration
public class InfluxConfig {
    @Value(value = "${username}")
    private String username;
    @Value(value = "${password}")
    private String password;
    @Value(value = "${openurl}")
    private String openurl;

    private InfluxDB influxDB;

    @Bean
    public InfluxDB influxDbBuild() {
        if (influxDB == null) {
            influxDB = InfluxDBFactory.connect(openurl, username, password);
        }
        return influxDB;
    }
}

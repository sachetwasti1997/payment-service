package com.sachet.payment_service.config.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DatabaseConfiguration {
    private String url;
    private String driverClassName;
    private String userName;
    private String password;
    private long connectionTimeOut;
    private long maxLifeTime;
    private int maxPoolSize;
    private String poolName;
}

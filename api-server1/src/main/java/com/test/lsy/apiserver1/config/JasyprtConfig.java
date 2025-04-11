package com.test.lsy.apiserver1.config;

import com.test.lsy.apiserver1.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class JasyprtConfig {

    @Value("${jasypt.password}")
    private String jasyptPassword;

    @Bean(name="jasyptStringEncryptor")
    public StringEncryptor stringEncryptor() {
        StringEncryptor encryptor = StringUtils.getEncryptor(jasyptPassword);
        return encryptor;
    }
}

package com.zte.crm.prm.yaml;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.config.ConfigFileApplicationListener;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.core.env.Profiles;

@SpringBootApplication
public class YamlApplication {

    public static void main(String[] args) {
        Profiles p;
        ConfigFileApplicationListener l;
        ConfigFileApplicationContextInitializer a;
        SpringApplication.run(YamlApplication.class, args);
    }

}

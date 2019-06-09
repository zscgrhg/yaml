package com.zte.crm.prm.yaml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class HelloRunner implements CommandLineRunner {
    @Value("${hello.world}")
    public String helloworld;

    @Autowired
    YmlCp ymlCp;
    @Autowired
    Environment env;
    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 10; i++) {
            System.out.println(helloworld);
        }
        String[] activeProfiles = env.getActiveProfiles();
        for (String activeProfile : activeProfiles) {
            System.out.println(activeProfile);
        }

    }
}

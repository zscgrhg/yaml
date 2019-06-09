package com.zte.crm.prm.yaml;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@ConfigurationProperties(prefix = "app")
public class YmlCp {
    List<String> ymllocaltion;

    public List<String> getYmllocaltion() {
        return ymllocaltion;
    }

    public void setYmllocaltion(List<String> ymllocaltion) {
        this.ymllocaltion = ymllocaltion;
    }
}

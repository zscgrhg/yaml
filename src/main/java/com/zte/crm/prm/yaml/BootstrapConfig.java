package com.zte.crm.prm.yaml;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.ClassPathResource;

import java.util.List;
import java.util.Properties;
import java.util.stream.Stream;

@Configuration
@EnableConfigurationProperties(BootstrapConfig.class)
@ConfigurationProperties(prefix = "app")
public class BootstrapConfig {

    List<String> ymllocaltion;

    public List<String> getYmllocaltion() {
        return ymllocaltion;
    }

    public void setYmllocaltion(List<String> ymllocaltion) {
        this.ymllocaltion = ymllocaltion;
    }

    @Bean
    public PropertySourcesPlaceholderConfigurer properties(ConfigurableEnvironment environment) {
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        propertySourcesPlaceholderConfigurer.setIgnoreResourceNotFound(true);

        for (String s : ymllocaltion) {
            System.out.println(s);
        }

        Properties[] properties = Stream.of(new String[]{""}, environment.getActiveProfiles())
                .flatMap(Stream::of)
                .map(p -> {
                    String ps = p.isEmpty() ? "" : "-" + p;
                    ClassPathResource classPathResource = new ClassPathResource(String.format("config/app%s.yml", ps));
                    return classPathResource;
                }).filter(ClassPathResource::exists)
                .map(p -> {
                    YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
                    yaml.setResources(p);
                    return yaml.getObject();
                }).toArray(Properties[]::new);


        propertySourcesPlaceholderConfigurer.setPropertiesArray(properties);

        return propertySourcesPlaceholderConfigurer;
    }


}

package com.zte.crm.prm.yaml;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.ClassPathResource;

import java.util.Properties;
import java.util.stream.Stream;

@Configuration
public class BootstrapConfig   {

    @Bean
    public  PropertySourcesPlaceholderConfigurer properties(ConfigurableEnvironment environment) {
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        propertySourcesPlaceholderConfigurer.setIgnoreResourceNotFound(true);

        Properties[] properties = Stream.of(new String[]{""}, environment.getActiveProfiles())
                .flatMap(Stream::of).map(p -> {
                    YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();

                    String ps=p.isEmpty()?"":"-"+p;
                    yaml.setResources(new ClassPathResource(String.format("config/app%s.yml", ps)));
                    return yaml.getObject();
                }).toArray(Properties[]::new);



        propertySourcesPlaceholderConfigurer.setPropertiesArray(properties);

        return propertySourcesPlaceholderConfigurer;
    }


}

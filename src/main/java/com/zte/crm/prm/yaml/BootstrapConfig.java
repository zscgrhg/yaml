package com.zte.crm.prm.yaml;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.util.List;
import java.util.Properties;
import java.util.stream.Stream;

@Configuration
@EnableConfigurationProperties(YmlCp.class)

public class BootstrapConfig {


    @Bean
    public PropertySourcesPlaceholderConfigurer properties(ConfigurableEnvironment environment) {


        try {
            find();
        } catch (Exception e) {
            e.printStackTrace();
        }
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        propertySourcesPlaceholderConfigurer.setIgnoreResourceNotFound(true);

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

    public List<String> find() throws Exception{
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath*:config/*.yml");
        for (Resource resource : resources) {
            String filename = resource.getFilename();
            System.out.println(filename);
        }

        return null;
    }

}

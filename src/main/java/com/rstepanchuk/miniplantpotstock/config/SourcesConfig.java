package com.rstepanchuk.miniplantpotstock.config;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class SourcesConfig {

  private static final String RESOURCES_PATH = "src/main/resources";

  // Component has been overridden to allow adding other component-specific properties without
  // revealing sensitive data in repository
  @Bean
  public PropertySourcesPlaceholderConfigurer properties() {
    PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
    YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
    yaml.setResources(getPathResources());
    pspc.setProperties(yaml.getObject());
    pspc.setIgnoreUnresolvablePlaceholders( true );
    return pspc;
  }

  private ClassPathResource[] getPathResources() {
    File resources = new File(RESOURCES_PATH);
    return getResourcesPaths(resources).stream()
        .map(path -> path.substring(RESOURCES_PATH.length()))
        .map(ClassPathResource::new)
        .toArray(ClassPathResource[]::new);
  }

  private List<String> getResourcesPaths(File directory) {
    List<String> result = new ArrayList<>();
    File[] files = directory.listFiles();
    if (files != null) {
      for (File file : files) {
        if (file.getName().endsWith(".yml")) {
          result.add(file.getPath());
        } else if (file.isDirectory()) {
          result.addAll(getResourcesPaths(file));
        }
      }
    }
    return result;
  }
}

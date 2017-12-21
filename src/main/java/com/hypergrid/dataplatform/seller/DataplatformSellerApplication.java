package com.hypergrid.dataplatform.seller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.hypergrid.dataplatform.seller.config.RabbitClientConfig;

@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = {"com.hypergrid.dataplatform.seller"})
public class DataplatformSellerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataplatformSellerApplication.class, args);
	}

  @Bean
  public ApplicationContext getAnnotationConfigApplicationContext() {
    return new AnnotationConfigApplicationContext(RabbitClientConfig.class);
  }
}

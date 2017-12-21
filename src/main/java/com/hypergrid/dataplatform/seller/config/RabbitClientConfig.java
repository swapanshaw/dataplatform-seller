package com.hypergrid.dataplatform.seller.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.remoting.client.AmqpProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hypergrid.common.service.BuyerTicketService;

@Configuration
public class RabbitClientConfig {
  @Bean
  public ConnectionFactory connectionFactory() {
    return new CachingConnectionFactory("localhost", 5762);
  }

  @Bean
  public Queue getQueue() {
    return new Queue("rpc_queue");
  }

  @Bean
  public RabbitTemplate template() {
    RabbitTemplate template = new RabbitTemplate(connectionFactory());
    template.setExchange("rpc");
    return template;
  }

  @Bean
  public AmqpProxyFactoryBean proxy(RabbitTemplate template) {
    AmqpProxyFactoryBean proxy = new AmqpProxyFactoryBean();
    proxy.setAmqpTemplate(template);
    proxy.setServiceInterface(BuyerTicketService.class);
    proxy.setRoutingKey("ticket");
    return proxy;
  }

}

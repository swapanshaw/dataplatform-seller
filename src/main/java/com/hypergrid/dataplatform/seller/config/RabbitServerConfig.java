package com.hypergrid.dataplatform.seller.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.remoting.service.AmqpInvokerServiceExporter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hypergrid.common.service.SellerTicketService;

@EnableAutoConfiguration
@Configuration
public class RabbitServerConfig {

  @Bean
  public ConnectionFactory connectionFactory() {
    return new CachingConnectionFactory("localhost", 5672);
  }

  @Bean
  public Queue getQueue() {
    return new Queue("rpc_status_queue");
  }

  @Bean
  public DirectExchange getDirectExchange() {
    return new DirectExchange("rpc");
  }

  @Bean
  public Binding getBinding(Queue queue, Exchange exchange) {
    return BindingBuilder.bind(queue).to(exchange).with("status").noargs();
  }

  @Bean
  public RabbitAdmin getAdmin(ConnectionFactory factory) {
    RabbitAdmin rabbitAdmin = new RabbitAdmin(factory);
    return rabbitAdmin;
  }

  @Bean
  public RabbitTemplate template() {
    RabbitTemplate template = new RabbitTemplate(connectionFactory());
    template.setExchange("rpc");
    return template;
  }

  @Bean
  public AmqpInvokerServiceExporter getAmqpInvokerServiceExporter(RabbitTemplate template,
      SellerTicketService service) {
    AmqpInvokerServiceExporter exporter = new AmqpInvokerServiceExporter();
    exporter.setAmqpTemplate(template);
    exporter.setService(service);
    exporter.setServiceInterface(SellerTicketService.class);
    return exporter;
  }

  @Bean
  public SimpleMessageListenerContainer getMessageContainer(ConnectionFactory connectionFactory, Queue queue,
      AmqpInvokerServiceExporter exporter) {
    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
    container.setQueues(queue);
    container.setMessageListener(exporter);
    return container;
  }
}
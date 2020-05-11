package com.directrice.user.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfiguration {

    @Value("${directrice.rabbitmq.queue}")
    private String queueName;

    @Value("${directrice.rabbitmq.exchange}")
    private String exchange;

    @Value("${directrice.rabbitmq.routingkey}")
    private String routingKey;

    //Step I Define Queue
    @Bean
    Queue emailVerification(){
        return new Queue(queueName,false);
    }

    //Step II Define Exchange between Sender and Receiver
    @Bean
    DirectExchange directExchange(){
        return new DirectExchange(exchange);
    }

    //Step III Biniding the Queue and the Exchanges
    @Bean
    Binding binding(Queue queue,DirectExchange directExchange){
       return BindingBuilder.bind(queue).to(directExchange).with(routingKey);
    }


    //Step VI Message Converter
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}

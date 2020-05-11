package com.directrice.user.utility.rabbitMQ;

import com.directrice.user.dto.Email;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${directrice.rabbitmq.exchange}")
    private String exchange;

    @Value("${directrice.rabbitmq.routingkey}")
    private String routingKey;


    public void send(Email email) {
        rabbitTemplate.convertAndSend(exchange, routingKey, email);
        System.out.println("Send msg = " + email);

    }

}

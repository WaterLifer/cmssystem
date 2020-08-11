package com.jiuyunedu.sky.config;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.DirectRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMqConfigure {

    // 消费者并发数量
    public static final int DEFAULT_CONCURRENT = 10;

    @Bean("directRabbitListenerContainerFactory")
    public DirectRabbitListenerContainerFactory directRabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        DirectRabbitListenerContainerFactory factory = new DirectRabbitListenerContainerFactory();
        factory.setConsumersPerQueue(DEFAULT_CONCURRENT);
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }

}

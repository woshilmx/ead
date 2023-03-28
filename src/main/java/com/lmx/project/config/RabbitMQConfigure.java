package com.lmx.project.config;


import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbitmq的配置类
 */
@Configuration
public class RabbitMQConfigure {
    // 使用Tocpic模式进行联系
    public static String TocpicExchangeName = "TocpicExchange";
    public static String quneq1 = "quneq1";
    public static String quneq2 = "quneq2";
    public static String dlxexchange = "dlxexchange";
    public static String dlxqueue = "dlxqueue";


    //    设置死信交换机
    @Bean("dlxexchange")
    public Exchange dlxexchange() {

        return ExchangeBuilder.topicExchange(dlxexchange).durable(true).build();
    }




    @Bean("dlxqueue")
    public Queue dlxqueue() {

        return QueueBuilder.durable(dlxqueue).build();
    }

    @Bean
    public Binding dlxbinding(Exchange dlxexchange, Queue dlxqueue) {
        return BindingBuilder.bind(dlxqueue).to(dlxexchange).with("dlx.#").and(null);
    }

//                创建交换机
    @Bean
    public Exchange TocpicExchange() {
        return ExchangeBuilder.topicExchange(TocpicExchangeName).durable(true).build();
    }


    @Bean
    public Queue queue1() {
        return QueueBuilder.durable(quneq1).deadLetterExchange(dlxexchange).deadLetterRoutingKey("dlx.123").build();
    }


    @Bean
    public Binding ExchangeBind(Exchange TocpicExchange, Queue queue1) {
        return BindingBuilder
                .bind(queue1)
                .to(TocpicExchange)
                .with("#.file")
                .and(null);
    }

//    @Bean
//    public Queue queue2() {
//        return QueueBuilder.durable(quneq2).build();
//    }


//    @Bean
//    public Binding ExchangeBind2(Exchange TocpicExchange, Queue queue2) {
//        return BindingBuilder
//                .bind(queue2)
//                .to(TocpicExchange)
//                .with("#.info")
//                .and(null);
//    }

//    @Bean
//    public Binding ExchangeBind3(Exchange TocpicExchange, Queue queue2) {
//        return BindingBuilder
//                .bind(queue2)
//                .to(TocpicExchange)
//                .with("#.warning")
//                .and(null);
//    }


}



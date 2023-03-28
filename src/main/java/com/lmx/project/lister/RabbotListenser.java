package com.lmx.project.lister;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.File;

/**
* 监听rabbitmq消息的组件
* */
@Component
public class RabbotListenser {
//    @RabbitListener(queues = "quneq1")
//    public void Lister(Message message){
//        String filepath = new String(message.getBody());
//        File file = new File(filepath);
//        boolean delete = file.delete();
//        if (delete){
//            System.out.println("删除成功");
//        }
//        System.out.println("error级别的消息是"+new String(message.getBody()));
//        System.out.println("交换机"+message.getMessageProperties().getReceivedExchange());
//        System.out.println("路由信息"+message.getMessageProperties().getReceivedRoutingKey());
//        System.out.println("============================");
//    }

//    @RabbitListener(queues = "quneq2")
//    public void Lister2(Message message){
//        System.out.println("info或waring级别的消息是"+new String(message.getBody()));
//        System.out.println("交换机"+message.getMessageProperties().getReceivedExchange());
//        System.out.println("路由信息"+message.getMessageProperties().getReceivedRoutingKey());
//        System.out.println("============================");
//    }

    @RabbitListener(queues = "dlxqueue")
    public void deletefile(Message message){
        System.out.println("死信队列中的信息:"+new String(message.getBody()));
        String filepath = new String(message.getBody());
        File file = new File(filepath);
        boolean delete = file.delete();
        if (delete){
            System.out.println("删除成功");
        }
    }

}

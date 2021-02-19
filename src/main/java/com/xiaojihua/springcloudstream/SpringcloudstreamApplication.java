package com.xiaojihua.springcloudstream;

import com.xiaojihua.springcloudstream.channels.Barista;
import com.xiaojihua.springcloudstream.domain.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.annotation.StreamRetryTemplate;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
//对应handle1
//@EnableBinding({Sink.class})
//对应handle2
//@EnableBinding({Processor.class})
//对应handle3
//@EnableBinding(Barista.class)
//对应handle4 5
@EnableBinding({Processor.class,Barista.class})
public class SpringcloudstreamApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudstreamApplication.class, args);
    }


    /**
     * 监听输入通道
     * 需要通过Rabbitmq控制台向INPUT exchanges发送消息，才能收到
     * SpringCloudStream自动将 message payload转换为Person类型
     * @param person
     */
//    @StreamListener(Sink.INPUT)
//    public void handle1(Person person){
//        System.out.println("reciveed:" + person);
//    }

    /**
     * 测试Processor的输入通道，接收到消息后输出到Processor对应的
     * 输出通道，但是输出通道对应的Rabbitmq交换机没有对应的消费者，所
     * 以没有进一步的消费
     * 需要通过Rabbitmq控制台向INPUT exchanges发送消息，才能收到
     * @param value
     * @return
     */
//    @StreamListener(Processor.INPUT)
//    @SendTo(Processor.OUTPUT)
//    public String handle2(String value){
//        System.out.println("reciveed:" + value);
//        return value.toUpperCase();
//    }


    /**
     * 自定义绑定  Barista.class
     * 自定义了orders hotDrinks coldDrinks等通道，springcloudStream会在rabbitmq中建立相应的交换机
     * @param value
     */
//    @StreamListener(value = "orders")
//    public void handle3(String value){
//        System.out.println("reciveed:" + value);
//    }

    /**
     * handle4 与 handle5是一套
     * 从Processor定义的INPUT通道接收消息
     * 然后转换成大写后发送到Barista定义的orders通道中
     * @param value
     * @return
     */
//    @StreamListener(Processor.INPUT)
//    @SendTo("orders")
//    public String handle4(String value){
//        System.out.println("从Processor，reciveed:" + value);
//        System.out.println("往orders发送:" + value);
//        return value.toUpperCase();
//    }

    /**
     * 监听Barista的orders通道，输出接收到的相关信息
     * @param value
     */
//    @StreamListener("orders")
//    public void handle5(String value){
//        System.out.println("从orders reciveed:" + value);
//    }


    /**
     * 测试@Payload @Header
     * @Payload用于标识是payload，当只有一个参数的时候可以不标识
     * @Header用于获取消息的指定header
     * 需要在rabbitmq控制台发送消息的时候，指定：header及payload
     * @param value
     * @param header
     */
    @StreamListener("orders")
    public void handle6(@Payload String value, @Header("header") String header){
        System.out.println("payload:" + value);
        System.out.println("header:" + header);
    }

}

package com.xiaojihua.springcloudstream;

import com.xiaojihua.springcloudstream.channels.Barista;
import com.xiaojihua.springcloudstream.channels.PolledConsumer;
import com.xiaojihua.springcloudstream.domain.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.annotation.StreamRetryTemplate;
import org.springframework.cloud.stream.binder.PollableMessageSource;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Map;

@SpringBootApplication
//对应handle1
//@EnableBinding({Sink.class})
//对应handle2
//@EnableBinding({Processor.class})
//对应handle3
//@EnableBinding(Barista.class)
//对应handle4 5 6
//@EnableBinding({Processor.class,Barista.class})
//对应handle 7 8
//@EnableBinding({Sink.class})
//对应handle 9 10
//@EnableBinding({PolledConsumer.class})
//对应handle 11 errorHandle1 errorHandle2
@EnableBinding({Sink.class})
public class SpringcloudstreamApplication {
    private final Logger logger = LoggerFactory.getLogger(SpringcloudstreamApplication.class);

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
//    @StreamListener("orders")
//    public void handle6(@Payload String value, @Header("header") String header){
//        System.out.println("payload:" + value);
//        System.out.println("header:" + header);
//    }


    /**
     *所有消息头中带有type并且值为bogey的消息，由此方法处理
     * @param person
     */
//    @StreamListener(target=Sink.INPUT,condition = "headers['type']=='bogey'")
//    public void handle7(@Payload Person person){
//        System.out.println("收到headers['type']=='bogey'的payload:" + person);
//    }

    /**
     *所有消息头中带有type并且值为bacall的消息，由此方法处理
     * @param person
     */
//    @StreamListener(target=Sink.INPUT,condition = "headers['type']=='bacall'")
//    public void handle8(@Payload Person person){
//        System.out.println("收到headers['type']=='bacall'的payload:" + person);
//    }

    /**
     * 测试使用轮询消息 消费者
     * @param destIn
     * @param destOut
     * @return
     */
//    @Bean
//    public ApplicationRunner handle9(PollableMessageSource destIn, MessageChannel destOut) {
//        return args -> {
//            while (true) {
//
//                boolean flag = destIn.poll(m -> {
//                    Map<String,Object> newPayload = (Map<String,Object>) m.getPayload();
//                    System.out.println("轮询消费者接收到消息："  + newPayload);
//                    destOut.send(new GenericMessage<>(newPayload));
//                },new ParameterizedTypeReference<Map<String,Object>>(){});
//                System.out.println(flag);
//                Thread.sleep(1000);
//            }
//
//
//        };
//    }

    /**
     * 监听PollableMessageSource 对应的output
     * @param value
     */
//    @StreamListener("destOut")
//    public void handle10(@Payload String value){
//        System.out.println("payload:" + value);
//    }

    /**
     * 测试异常处理
     * @param person
     */
    @StreamListener(Sink.INPUT)
    public void handle11(Person person){
        //接收消息后即抛出异常
        throw new RuntimeException("BOOM!");
    }

    /**
     * 具体绑定级别的异常处理
     * 监控某个具体的绑定，如：现在监控的是Sink.INPUT这个绑定的异常
     * inputChannel： 绑定名.组名.errors
     * 其中组名需要在配置文件中指定，不能使用匿名的。
     *
     * 对于异常处理方法需要使用@ServiceActivator或@Transformer（这个注解还需要调试和进一步学习）
     * @param message
     */
//    @ServiceActivator(inputChannel = Sink.INPUT + ".myGroup.errors")
//    public void errorHandle1(Message<?> message){
//        System.out.println("errorHandle1 Handling error:" + message);
//    }

    /**
     * 全局异常处理，经测试虽然对异常进行了处理，但还是会抛出
     * @param message
     */
//    @StreamListener("errorChannel")
//    public void errorHandle2(Message<?> message){
//        System.out.println("errorHandle2 Handling error:" + message);
//    }

    
}

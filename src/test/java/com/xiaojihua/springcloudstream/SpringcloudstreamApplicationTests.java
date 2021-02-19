package com.xiaojihua.springcloudstream;

import com.xiaojihua.springcloudstream.channels.Barista;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.messaging.Sink;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class SpringcloudstreamApplicationTests {

    @Autowired
    private Sink sink;
    @Autowired
    private Barista barista;

    /**
     * 测试 Sink接口的自动实现
     */
    @Test
    void contextLoads() {
        assertNotNull(this.sink.input());
    }

    /**
     * 测试 自定义通道接口的自动实现
     */
    @Test
    void contextLoads1() {
        assertNotNull(this.barista.orders());
    }


}

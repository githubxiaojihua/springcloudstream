package com.xiaojihua.springcloudstream;

import com.xiaojihua.springcloudstream.channels.Barista;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SpringcloudstreamApplicationTests {

    @Autowired
    private Sink sink;

    /**
     * 测试 Sink接口的自动实现
     */
    @Test
    public void contextLoads() {
        assertNotNull(this.sink.input());
    }




}

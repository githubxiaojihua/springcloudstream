package com.xiaojihua.springcloudstream.channels;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.binder.PollableMessageSource;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface PolledConsumer {
    @Input
    PollableMessageSource destIn();

    @Output
    MessageChannel destOut();
}

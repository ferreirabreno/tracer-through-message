package dev.breno.tracerthroughmessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
public class HelloWorldService {

    @Autowired private MyTopicPublisher myTopicPublisher;

    public void helloWorld(Map<String, String> tracingHeaders) {
        SimpleMessage message = new SimpleMessage(UUID.randomUUID(), "WAITING", 15);
        myTopicPublisher.publishMessage(message, tracingHeaders);
    }
}

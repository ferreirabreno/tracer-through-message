package dev.breno.tracerthroughmessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class HelloWorldController {

    @Autowired MyTopicPublisher myTopicPublisher;

    @GetMapping("/hello-world")
    private ResponseEntity<?> helloWorld() {
        Map<String, String> tracingHeaders = new HashMap<>();
        tracingHeaders.put("tracerId", UUID.randomUUID().toString());
        tracingHeaders.put("spanId", UUID.randomUUID().toString());
        tracingHeaders.put("correlationId", UUID.randomUUID().toString());

        SimpleMessage message = new SimpleMessage(UUID.randomUUID(), "WAITING", 15);
        myTopicPublisher.publishMessage(message, tracingHeaders);
        return ResponseEntity.noContent().build();
    }
}

package dev.breno.tracerthroughmessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @Autowired MyTopicPublisher myTopicPublisher;

    @GetMapping("/hello-world")
    private ResponseEntity<?> helloWorld() {
        myTopicPublisher.publishMessage("Hello world!");
        return ResponseEntity.noContent().build();
    }
}

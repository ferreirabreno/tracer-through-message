package dev.breno.tracerthroughmessage.hello_world;

import dev.breno.tracerthroughmessage.utils.HttpResponseUtils;
import dev.breno.tracerthroughmessage.Observability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HelloWorldController {

    @Autowired private HelloWorldService service;

    @GetMapping("/hello-world")
    private ResponseEntity<?> helloWorld() {
        Map<String, String> tracingHeaders = Observability.getTracingHeaders();
        service.helloWorld(tracingHeaders);
        HttpHeaders httpHeaders = HttpResponseUtils.toHttpHeaders(tracingHeaders);
        return ResponseEntity.noContent().headers(httpHeaders).build();
    }

}

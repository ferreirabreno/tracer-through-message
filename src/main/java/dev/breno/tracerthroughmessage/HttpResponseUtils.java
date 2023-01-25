package dev.breno.tracerthroughmessage;

import org.springframework.http.HttpHeaders;

import java.util.Map;

public class HttpResponseUtils {

    public static HttpHeaders toHttpHeaders(Map<String, String> tracingHeaders) {
        HttpHeaders httpHeaders = new HttpHeaders();
        tracingHeaders.forEach(httpHeaders::add);
        return httpHeaders;
    }

}

package dev.breno.tracerthroughmessage;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Observability {

    public static Map<String, String> getTracingHeaders() {
        Map<String, String> tracingHeaders = new HashMap<>();
        tracingHeaders.put("tracerId", UUID.randomUUID().toString());
        tracingHeaders.put("spanId", UUID.randomUUID().toString());
        tracingHeaders.put("correlationId", UUID.randomUUID().toString());
        return tracingHeaders;
    }

}

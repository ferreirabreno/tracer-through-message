package dev.breno.tracerthroughmessage;

import java.time.LocalDateTime;
import java.util.Map;

public record TopicMessage(
        String type,
        String messageId,
        String token,
        String topicArn,
        String message,
        String unsubscribeURL,
        String signatureVersion,
        String signature,
        String signingCertUrl,
        LocalDateTime timestamp,
        Map<String, Object> messageAttributes
) { }
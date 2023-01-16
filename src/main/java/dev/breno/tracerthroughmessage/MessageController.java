package dev.breno.tracerthroughmessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MessageController {

    @Autowired private SnsService snsService;

    @Value("${app.aws.sns.myTopic.arn}")
    private String myTopicArn;

    public void start(String message) {
        snsService.sendMessage(myTopicArn, message);
    }

}

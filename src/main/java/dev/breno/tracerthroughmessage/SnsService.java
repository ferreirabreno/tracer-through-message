package dev.breno.tracerthroughmessage;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SnsService {

    @Autowired private AmazonSNS amazonSNS;

    public void sendMessage(String topicArn, String message) {
        PublishRequest publishRequest = new PublishRequest(topicArn, message);
        amazonSNS.publish(publishRequest);
    }
}

package com.code.springboot;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
public class WikimediaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(WikimediaProducer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage() throws InterruptedException {
        String topic = "wikimedia";
        String url = "https://stream.wikimedia.org/v2/stream/recentchange";
        EventHandler event = new WikimediaChangeHandler(kafkaTemplate, topic);
        EventSource.Builder builder = new EventSource.Builder(event, URI.create(url));
        EventSource source = builder.build();

        source.start();

        TimeUnit.MINUTES.sleep(10);
    }
}

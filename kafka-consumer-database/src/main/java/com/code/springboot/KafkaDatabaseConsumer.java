package com.code.springboot;

import com.code.springboot.entity.WikimediaData;
import com.code.springboot.repository.WikiDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaDatabaseConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaDatabaseConsumer.class);
    @Autowired
    private WikiDataRepository wikiDataRepository;

    @KafkaListener(topics = "wikimedia",groupId = "myGroup")
    public void consume(String eventMessage){
        LOGGER.info(String.format("saved to dataBase"));
        WikimediaData wikimediaData = new WikimediaData();
        wikimediaData.setWikiEventData(eventMessage);
        wikiDataRepository.save(wikimediaData);
    }
}

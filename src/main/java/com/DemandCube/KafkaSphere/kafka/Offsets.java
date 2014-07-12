package com.DemandCube.KafkaSphere.kafka;

import com.DemandCube.KafkaSphere.kafka.Metadata;

import kafka.javaapi.TopicMetadata;
import kafka.javaapi.TopicMetadataRequest;
import kafka.javaapi.consumer.SimpleConsumer;

import java.util.*;

/**
 * Created by stites on 7/8/14.
 */
public class Offsets {

  public static void main(String[] args) {
    TopicLeaders();
  }


  public static HashMap<String, String> TopicLeaders(){
    Metadata metadata = new Metadata();
    HashMap<String, HashMap<String, String>> details = metadata.getDetails();
    HashMap<String, String> topicLeaders = new HashMap<>();

    // iterate through the items so that we can get the topic leaders
    Iterator it = details.entrySet().iterator();
    while (it.hasNext()) {
      Map.Entry pairs = (Map.Entry)it.next();
      String topic = pairs.getKey().toString();
      HashMap<String, String> topicData = (HashMap)pairs.getValue();
      String leader = topicData.get("leader");
      topicLeaders.put(topic, leader);
    }

    return topicLeaders;
  };
}

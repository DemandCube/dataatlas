package com.DemandCube.KafkaSphere.kafka;


import kafka.javaapi.TopicMetadataRequest;
import kafka.javaapi.consumer.SimpleConsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;


public class Metadata {

  private static HashMap<String, HashMap<String, String>> current;

  public static void main(String[] args) {
    fetch();
  }

  public static HashMap<String, HashMap<String, String>> get(){
    return current;
  };

  public static void fetch(){
    HashMap<String, HashMap<String, String>> brokerDetails = new HashMap<>();
    List<kafka.javaapi.TopicMetadata> data = MetadataDump();
    HashMap<String, String> topicDetails = new HashMap<>();
    String topic;

    for (kafka.javaapi.TopicMetadata item : data){
      topic = item.topic();
      for (kafka.javaapi.PartitionMetadata part: item.partitionsMetadata()){
        String replicas = "";
        String isr = "";
        for (kafka.cluster.Broker replica: part.replicas()){
          replicas += " " + replica.host();
        }
        for (kafka.cluster.Broker replica: part.isr()){
          isr += " " + replica.host();
        }
        topicDetails.put("partition", String.valueOf(part.partitionId()) );
        topicDetails.put("leader", part.leader().host() );
        topicDetails.put("replicas", "[" + replicas + "]");
        topicDetails.put("isr", "[" + isr + "]");
      }

      brokerDetails.put(topic, topicDetails);
    }
    current = brokerDetails;
  }

  public static List<kafka.javaapi.TopicMetadata> MetadataDump(){
    kafka.javaapi.consumer.SimpleConsumer consumer = new SimpleConsumer("0.0.0.0", 9092, 100000, 64 * 1024, "metadata");
    List<String> topics = new ArrayList<String>();
    TopicMetadataRequest req = new TopicMetadataRequest(topics);
    kafka.javaapi.TopicMetadataResponse res = consumer.send(req);
    return res.topicsMetadata();
  };
}

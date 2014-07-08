package com.DemandCube.KafkaSphere.kafka;


import kafka.javaapi.TopicMetadataRequest;
import kafka.javaapi.consumer.SimpleConsumer;

import java.util.ArrayList;
import java.util.List;


public class metrics {
    public static void main(String[] args){
      List<kafka.javaapi.TopicMetadata> data = MetaDataDump();
      for (kafka.javaapi.TopicMetadata item : data){
        System.out.println("topic: " + item.topic());
        for (kafka.javaapi.PartitionMetadata part: item.partitionsMetadata()){
          String replicas = "";
          String isr = "";
          for (kafka.cluster.Broker replica: part.replicas()){
            replicas += " " + replica.host();
          }
          for (kafka.cluster.Broker replica: part.isr()){
            isr += " " + replica.host();
          }
          System.out.println("    Partition: " + part.partitionId());
          System.out.println("    Leader   : " + part.leader().host());
          System.out.println("    Replicas : [" + replicas + "]");
          System.out.println("    ISR      : [" + isr + "]");
        }
      }
    }

    public static List<kafka.javaapi.TopicMetadata> MetaDataDump(){
      kafka.javaapi.consumer.SimpleConsumer consumer = new SimpleConsumer("0.0.0.0", 9092, 100000, 64 * 1024, "metadata");
      List<String> topics2 = new ArrayList<String>();
      TopicMetadataRequest req = new TopicMetadataRequest(topics2);
      kafka.javaapi.TopicMetadataResponse res = consumer.send(req);
      List<kafka.javaapi.TopicMetadata> data3 = res.topicsMetadata();
      return data3;
    };
}

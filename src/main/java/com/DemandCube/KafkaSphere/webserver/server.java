package com.DemandCube.KafkaSphere.webserver;

import static spark.Spark.*;

import com.DemandCube.KafkaSphere.ServerMessage;
import com.DemandCube.KafkaSphere.SparkConsumerGroup;
import spark.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Date;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonMappingException;

import kafka.api.OffsetRequest;
import kafka.api.PartitionOffsetRequestInfo;
import kafka.common.BrokerNotAvailableException;
import kafka.common.TopicAndPartition;
import org.apache.zookeeper.data.Stat;

/**
 * A RESTful server built on the Spark Framework (sparkjava.com).
 **/
public class server {

  private static Map<String, String> toJSON(String message) {

    ServerMessage serverMsg = new ServerMessage(message);
    Map<String,String> map  = new HashMap<String,String>();

    String value = "";

    try {
      ObjectMapper mapper = new ObjectMapper();
      value = mapper.writeValueAsString(serverMsg);
      map = mapper.readValue(value, 
        new TypeReference<HashMap<String,String>>(){});
    } 
    catch (JsonGenerationException e) {
     e.printStackTrace();
    } catch (JsonMappingException e) {
     e.printStackTrace();
    } catch (IOException e) {
     e.printStackTrace();
    }
   return map;
 }

 public static void main(String[] args) {

  //  setPort(5678); <- Uncomment this if you wan't spark to listen to a different port

    get(new Route("/") {
      @Override
      public Object handle(Request request, Response response) {
        return "Kafkashere up. navigate to 'sphere/api' for Kafkashere interface and 'kafka/api' for kafka interface";
      }
    });

    // KAFKA ROUTES

     get(new Route("/kafka/api/:topic") {
         @Override
         public Object handle(Request request, Response response) {
             String topic = request.params(":topic");

             SparkConsumerGroup simpleConsumer = new SparkConsumerGroup("localhost:2181", "test-consumer-group", topic);

             simpleConsumer.run(1);

             return topic;
         }
     });

     get(new Route("/sphere/api/:topic") {
         @Override
         public Object handle(Request request, Response response) {
             String topic = request.params(":topic");
             System.out.println(Stat.signature());
             return topic;
         }
     });

    post(new Route("/kafka/api/:topic") {
      @Override
      public Object handle(Request request, Response response) {
        String topic = request.params(":topic");
        String message = request.queryParams("message");

                // config the broker to send messages to
        Properties props = new Properties();
        props.put("metadata.broker.list", "localhost:9092");
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("request.required.acks", "1");
        ProducerConfig config = new ProducerConfig(props);

                // initialize the producer
        Producer<String, String> producer = new Producer<String, String>(config);

                // craft a message:
        Random rnd = new Random();
        long runtime = new Date().getTime();
        String ip = "192.168.2." + rnd.nextInt(255);
        String msg = "[" + runtime + "] www.example.com - " + ip + "\nmessage:" +message;

        KeyedMessage<String, String> data = new KeyedMessage<String, String>(topic, ip, msg);

        producer.send(data);

        return "Success!\ntopic: "+topic+"\n" + msg+"\n";
      }
    });

  }
}




package com.DemandCube.KafkaSphere;

import static spark.Spark.*;
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


/**
 * A RESTful server built on the Spark Framework (sparkjava.com).
 **/
public class NachtsServer {

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
        return "Hello You!";
      }
    });

    get(new Route("/hello") {
      @Override
      public Object handle(Request request, Response response) {
        return toJSON("Hola!");
      }
    });

    get(new Route("/private") {
      @Override
      public Object handle(Request request, Response response) {
        response.status(401);
        return toJSON("Private Route");
      }
    });

    get(new Route("/users/:name") {
      @Override
      public Object handle(Request request, Response response) {
        return toJSON("User: " + request.params(":name"));
      }
    });

    get(new Route("/news/:section") {
      @Override
      public Object handle(Request request, Response response) {
        response.type("text/xml");
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><news>" + request.params("section") + "</news>";
      }
    });

    get(new Route("/protected") {
      @Override
      public Object handle(Request request, Response response) {
        halt(403, "Protected Route");
        return null;
      }
    });

    get(new Route("/redirect") {
      @Override
      public Object handle(Request request, Response response) {
        response.redirect("/news/bigdata");
        return null;
      }
    });

    get(new Route("/api/:topic") {
      @Override
      public Object handle(Request request, Response response) {
        String topic = request.params(":topic");

        SparkConsumerGroup simpleConsumer = new SparkConsumerGroup("localhost:2181", "test-consumer-group", topic);

        simpleConsumer.run(1);

        return topic;
      }
    });

  }
}




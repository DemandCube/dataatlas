package kafkasphere;
import static spark.Spark.*;
import spark.*;


/**
 * A RESTful server built on the Spark Framework (sparkjava.com).
 **/
public class NachtsServer {
    public static void main(String[] args) {
        get(new Route("/") {
            @Override
            public Object handle(Request request, Response response) {
                return "Hello World!";
            }
        });
    }
}
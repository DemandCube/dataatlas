package kafkasphere;
import static spark.Spark.*;
import spark.*;


/**
 * A RESTful server built on the Spark Framework (sparkjava.com).
 **/
public class GregorServer {
    public static void main(String[] args) {
        get(new Route("/") {
            @Override
            public Object handle(Request request, Response response) {
                return "As Gregor Samsa awoke one morning from uneasy dreams he found himself transformed in his bed into a monstrous vermin...";
            }
        });
    }
}
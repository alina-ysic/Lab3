package systems.lab3;

import org.apache.hadoop.io.Text;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

public class FlightApp {

    private static final String DELIMITER_COMMA = ",";
    private static final String DELIMITER_QUOTE = "\"";

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("lab3");
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> airportFile = sc.textFile("L_AIRPORT_ID.csv");
        JavaPairRDD<Tuple2, FlightSerializable> wordsWithCount = airportFile.mapToPair(
                value -> {
                    String[] flightInfo = value.replace(DELIMITER_QUOTE, "").split(DELIMITER_COMMA);

                    int airportId = Integer.parseInt(flightInfo[CODE_POS]);
                    String delay = flightInfo[DELAY_POS];
                    if (!delay.isEmpty() && Float.parseFloat(delay) != 0) {
                        context.write(new AirportIdWritableComparable(airportId, INDICATOR), new Text(delay));
                    }


                    return null;
                }
        );
        wordsWithCount.collect().forEach(t -> System.out.println("Key:" + t._1() + " Value:" + t._2()));
    }
}

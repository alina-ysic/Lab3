package systems.lab3;

import org.apache.hadoop.io.Text;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Objects;

public class FlightApp {

    private static final String DELIMITER_COMMA = ",";
    private static final String DELIMITER_QUOTE = "\"";
    private static final int OUT_CODE_POS = 11;
    private static final int IN_CODE_POS = 14;
    private static final int DELAY_POS = 18;
    private static final int CANCELLED_POS = 19;

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("lab3");
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> airportFile = sc.textFile("664600583_T_ONTIME_sample.csv");
        airportFile = airportFile.filter((s) -> {
            String[] flightInfo = s.replace(DELIMITER_QUOTE, "").split(DELIMITER_COMMA);
            return !Objects.equals(flightInfo[0], "YEAR");
        });
        JavaPairRDD<Tuple2, FlightSerializable> wordsWithCount = airportFile.mapToPair(
                value -> {
                    String[] flightInfo = value.replace(DELIMITER_QUOTE, "").split(DELIMITER_COMMA);
                    int outAirportId = Integer.parseInt(flightInfo[OUT_CODE_POS]);
                    int inAirportId = Integer.parseInt(flightInfo[IN_CODE_POS]);
                    float delay = (flightInfo[DELAY_POS].isEmpty()) ? 0 : Float.parseFloat(flightInfo[DELAY_POS]);
                    boolean cancelled = Float.parseFloat(flightInfo[CANCELLED_POS]) == 1.00;

                    return new Tuple2<>(new Tuple2(outAirportId, inAirportId), new FlightSerializable(delay, cancelled));
                }
        );
        wordsWithCount.collect().forEach(t -> System.out.println("Key:" + t._1() + " Value:" + t._2()));
    }
}

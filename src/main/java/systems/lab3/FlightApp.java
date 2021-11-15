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
        JavaRDD<String> flightFile = sc.textFile("664600583_T_ONTIME_sample.csv");
        JavaRDD<String> airportFile = sc.textFile("L_AIRPORT_ID.csv");
        flightFile = flightFile.filter((s) -> {
            String[] flightInfo = s.replace(DELIMITER_QUOTE, "").split(DELIMITER_COMMA);
            return !Objects.equals(flightInfo[0], "YEAR");
        });
        JavaPairRDD<Tuple2, FlightsSerializable> airportsWithInfo = flightFile.mapToPair(
                value -> {
                    String[] flightInfo = value.replace(DELIMITER_QUOTE, "").split(DELIMITER_COMMA);
                    int outAirportId = Integer.parseInt(flightInfo[OUT_CODE_POS]);
                    int inAirportId = Integer.parseInt(flightInfo[IN_CODE_POS]);
                    float delay = (flightInfo[DELAY_POS].isEmpty()) ? 0 : Float.parseFloat(flightInfo[DELAY_POS]);
                    float cancelled = Float.parseFloat(flightInfo[CANCELLED_POS]);

                    return new Tuple2<>(new Tuple2(outAirportId, inAirportId), new FlightsSerializable(delay, cancelled, 1));
                }
        );
        JavaPairRDD<Tuple2, FlightsSerializable> result = airportsWithInfo.reduceByKey((x, y) -> {
            float maxDelay = Math.max(x.getDelay(), y.getDelay());
            int count = x.getCount() + y.getCount();
            float cancelledPercent = (x.getCancelledPercent() * x.getCount() + y.getCancelledPercent() * y.getCount()) / count;
            float latePercent = (x.getLatePercent() * x.getCount() + y.getLatePercent() * y.getCount()) / count;
            return new FlightsSerializable(maxDelay, cancelledPercent, latePercent, count);
        });
        result.collect().forEach(t -> System.out.println("Key:" + t._1() + " Value:" + t._2()));
    }
}

package systems.lab3;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;

public class FlightApp {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("lab3");
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> airportFile = sc.textFile("L_AIRPORT_ID.csv");
        System.out.println("AAAAA");
        /*JavaRDD<String> splitted = airportFile.flatMap(
                s -> Arrays.stream(s.split(" ")).iterator()
        );*/
        JavaPairRDD<String, Long> wordsWithCount = airportFile.mapToPair(
                s -> {
                    System.out.println("AAAAA");
                    System.out.println(s);

                    return new Tuple2<>(s, 1l);
                }
        );
    }
}

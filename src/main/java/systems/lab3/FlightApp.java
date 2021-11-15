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
        JavaPairRDD<String, String> airportFile = sc.textFile("L_AIRPORT_ID.csv");
        System.out.println("AAAAA");
        JavaPairRDD<String, Long> wordsWithCount = airportFile.mapToPair(
                s -> new Tuple2<>(s, 1l)
        );
        wordsWithCount.collect().forEach(t -> System.out.println("Key:" + t._1() + " Value:" + t._2()));
    }
}

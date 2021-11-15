package systems.lab3;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class FlightApp {
    SparkConf conf = new SparkConf().setAppName("lab3");
    JavaSparkContext sc = new JavaSparkContext(conf);
    JavaRDD<String> airportFile = sc.textFile("L_AIRPORT_ID.csv");
}

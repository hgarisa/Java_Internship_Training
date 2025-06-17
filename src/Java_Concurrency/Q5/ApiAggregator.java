package Java_Concurrency.Q5;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.Supplier;


 //Asynchronous API Aggregation with CompletableFuture
   //  Simulate an aggregator service that calls multiple APIs concurrently and combines their responses.


// Main class simulates API aggregator
public class ApiAggregator {

    public static CompletableFuture<String> callWeatherService() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}
            return "Weather: Sunny";
        });
    }

    public static CompletableFuture<String> callTrafficService() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {}
            return "Traffic: Light";
        });
    }

    public static CompletableFuture<String> callNewsService() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {}
            return "News: Market up";
        });
    }

    public static void main(String[] args) throws Exception {
        CompletableFuture<String> weather = callWeatherService();
        CompletableFuture<String> traffic = callTrafficService();
        CompletableFuture<String> news = callNewsService();

        CompletableFuture<Void> all = CompletableFuture.allOf(weather, traffic, news);
        all.join();

        System.out.println(weather.get());
        System.out.println(traffic.get());
        System.out.println(news.get());
    }

}

/*

CompletableFuture allows full non-blocking concurrent API calls.
Real-world: Microservice aggregators, API gateways.



* */


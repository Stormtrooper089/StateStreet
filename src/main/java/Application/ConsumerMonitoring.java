package Application;

import java.util.ArrayList;
import java.util.List;

public class ConsumerMonitoring {
    public static List<ListenerTemplate> listOfConsumers = new ArrayList<>();

    public static List<ListenerTemplate> getConsumerList() {
        return listOfConsumers;
    }

    public static void addConsumer(ListenerTemplate listener) {
        System.out.println("Sjdabjbsjbvkjsbjksbjkvvvvvzkvjdssssssssssssssssssssssjk");
        listOfConsumers.add(listener);
    }
}

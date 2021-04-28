package Application.SharedDS;

import Application.Producer.Generator;
import Application.Util.ConsumerMonitoring;
import Application.Util.PrimeNumber;
import Application.Supplier.InputConsumer;
import Application.Supplier.ListenerTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

public class SharedDataStructure {


    private static PriorityBlockingQueue<Integer> inputToBeProcessed;

    private static PriorityBlockingQueue<PrimeNumber> outputGenerated;

    private static SharedDataStructure sharedDataStructure = null;
    ExecutorService executorService = Executors.newScheduledThreadPool(20);
    private List<ListenerTemplate> lst = new ArrayList<>();
    ;
    private List<ListenerTemplate> cm;
    private InputConsumer ipc;

    public static SharedDataStructure getSharedDataStructure() {
        if (sharedDataStructure == null) {
            return new SharedDataStructure();
        } else {
            return sharedDataStructure;
        }
    }

    public void setup(int n) {
        if (n == 1) {
            inputToBeProcessed = new PriorityBlockingQueue<>();
            outputGenerated = new PriorityBlockingQueue<>();
            ConsumerMonitoring.addConsumer(new InputConsumer());
        }
    }

    public void put(Integer a) {
        inputToBeProcessed.add(a);
        start();
    }


    public Integer take() throws InterruptedException {
        Integer value = inputToBeProcessed.take();
        return value;
    }


    public void start() {
        cm = ConsumerMonitoring.getConsumerList();
        executorService.execute(new Runnable() {

            @Override
            public void run() {
                while (!inputToBeProcessed.isEmpty()) {
                    try {
                        Integer input = inputToBeProcessed.take();
                        for (ListenerTemplate listener :
                                ConsumerMonitoring.getConsumerList()) {
                            PrimeNumber resultOfPrimeChecked = listener.invoke(input);
                            System.out.println(" " + resultOfPrimeChecked.toString());
                        }

                    } catch (InterruptedException | IOException e) {

                    }
                }

            }
        });
    }

}

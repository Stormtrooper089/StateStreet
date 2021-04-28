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

    public InputConsumer getIpc() {
        return ipc;
    }

    public void setIpc(InputConsumer ipc) {
        this.ipc = ipc;
    }


    public void setup(int n) {
        if (n == 1) {
            inputToBeProcessed = new PriorityBlockingQueue<>();
            outputGenerated = new PriorityBlockingQueue<>();
            ConsumerMonitoring.addConsumer(new InputConsumer());
        }
        if (n == 2) {
            System.out.println(SharedDataStructure.getSharedDataStructure().getIpc());
            ConsumerMonitoring.addConsumer(SharedDataStructure.getSharedDataStructure().getIpc());
        }

    }


    public void put(Integer a) {
        inputToBeProcessed.add(a);
        start();
    }

    public void put(PrimeNumber a) {
        outputGenerated.add(a);
    }

    public Integer take() throws InterruptedException {
        Integer value = inputToBeProcessed.take();
        return value;
    }

    public PrimeNumber takeOutput() throws InterruptedException {
        PrimeNumber value = outputGenerated.take();
        return value;
    }

    public void start() {
        // System.out.println("the scheduler number of elements in list " + inputToBeProcessed.size());
        cm = ConsumerMonitoring.getConsumerList();
        //System.out.println("the size of listener  " + cm.size());
        //System.out.println("the size of lst  " + lst.size());
        executorService.execute(new Runnable() {

            @Override
            public void run() {
                while (!inputToBeProcessed.isEmpty()) {
                    try {
                        Integer input = inputToBeProcessed.take();
                        //Runnable r = new InputConsumer(input,outputGenerated);
                        //new Thread(r).start();
                        for (ListenerTemplate listener :
                                ConsumerMonitoring.getConsumerList()) {
                            System.out.println("Listener jinda hai ");
                            PrimeNumber resultOfPrimeChecked = listener.invoke(input);
                            System.out.println("the number checked is   " + resultOfPrimeChecked.getInput() + "  and it is prime  " + resultOfPrimeChecked.isPrime());
                            //outputGenerated.put(resultOfPrimeChecked);
                            //System.out.println("Kitna banaya hai   " + outputGenerated.size());
                        }

                    } catch (InterruptedException | IOException e) {

                    }
                }

            }
        });
    }

}

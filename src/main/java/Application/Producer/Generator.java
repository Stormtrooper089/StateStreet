package Application.Producer;

import Application.PrimeNumber;
import Application.SharedDS.SharedDataStructure;

public class Generator {


    private SharedDataStructure sharedQueue;

    public void run() throws InterruptedException {
        sharedQueue = SharedDataStructure.getSharedDataStructure();
        //System.out.println("dfbhbsbskbvfvk"  + sharedQueue);
        generate();
        receive();
    }

    public void generate() {
        int j = 2;
        sharedQueue.setup(1);
        sharedQueue.start();
        while (true) {
            // System.out.println("dfbhbsbskbvfvk "  +  j);


            sharedQueue.put(j);

            j++;
        }
    }

    public void receive() throws InterruptedException {
        System.out.println("yaha aaugjo ");
        while (true) {
            PrimeNumber number = SharedDataStructure.getSharedDataStructure().takeOutput();
            number.toString();
            System.out.println(number.isPrime() + "  " + "     jfdbjdsbdks");
        }
    }
}

package Application.Supplier;

import Application.Supplier.InputConsumer;

public class InputConsumerRunner {
    public static void main(String[] args) throws InterruptedException {
        InputConsumer ipc = new InputConsumer();
        ipc.start();
    }
}

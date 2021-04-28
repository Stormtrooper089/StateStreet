package Application.Supplier;

import Application.Supplier.InputConsumer;

import java.io.IOException;

public class InputConsumerRunner {
    public static void main(String[] args) throws InterruptedException, IOException {
        InputConsumer ipc = new InputConsumer();
        ipc.start();
    }
}

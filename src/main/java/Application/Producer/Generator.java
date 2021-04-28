package Application.Producer;

import Application.Util.PrimeNumber;
import Application.SharedDS.SharedDataStructure;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Generator {


    public static final int MAX_RANDOM_NUMBER = Integer.MAX_VALUE;
    public void run() throws InterruptedException {

        //System.out.println("dfbhbsbskbvfvk"  + sharedQueue);


        setupServer();

        //receive();
    }

    private void setupServer() {
        Socket socket = null;
        ServerSocket server = null;
        DataInputStream in = null;
        DataOutputStream out = null;
        try {
            server = new ServerSocket(63811);
            System.out.println("Server started");

            System.out.println("Waiting for a client ...");

            socket = server.accept();
            System.out.println("Client accepted");
            //generate();
            // takes input from the client socket
            in = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));

            String line = "";
            new Thread(new RandommGenerator()).start();
            // reads message from client until "Over" is sent
            try {
                //out.writeInt(1);
                System.out.println("Yaha kabhi aya kya      ");
                int number = in.readInt(); // wait until response arrives
                if (number != 0) {
                    throw new RuntimeException("verification check failed"); // FIXME: improve
                }
                boolean isPrime = in.readBoolean();

                //return new PrimeResult(number, isPrime);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Closing connection");

            // close connection
            socket.close();
            in.close();
        } catch (IOException i) {
            System.out.println(i);
        }

    }

    class RandommGenerator implements Runnable {
        private final Random randomGenerator = new Random();
        SharedDataStructure sharedQueue = SharedDataStructure.getSharedDataStructure();

        @Override
        public void run() {
            sharedQueue.setup(1);
            while (true) {
                int randomInt = Math.abs(randomGenerator.nextInt(MAX_RANDOM_NUMBER));
                sharedQueue.put(randomInt);
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
}

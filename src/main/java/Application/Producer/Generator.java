package Application.Producer;

import Application.Util.PrimeNumber;
import Application.SharedDS.SharedDataStructure;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
public class Generator {


    private SharedDataStructure sharedQueue;

    public void run() throws InterruptedException {
        sharedQueue = SharedDataStructure.getSharedDataStructure();
        //System.out.println("dfbhbsbskbvfvk"  + sharedQueue);
        setupServer();

        receive();
    }

    private void setupServer() {
        Socket socket = null;
        ServerSocket server = null;
        DataInputStream in = null;
        try {
            server = new ServerSocket(63811);
            System.out.println("Server started");

            System.out.println("Waiting for a client ...");

            socket = server.accept();
            System.out.println("Client accepted");
            generate();
            // takes input from the client socket
            in = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));

            String line = "";

            // reads message from client until "Over" is sent
            while (!line.equals("Over")) {
                try {
                    line = in.readUTF();
                    System.out.println(line);

                } catch (IOException i) {
                    System.out.println(i);
                }
            }
            System.out.println("Closing connection");

            // close connection
            socket.close();
            in.close();
        } catch (IOException i) {
            System.out.println(i);
        }

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

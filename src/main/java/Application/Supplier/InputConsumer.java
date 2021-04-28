package Application.Supplier;

import Application.Util.PrimeNumber;
import Application.SharedDS.SharedDataStructure;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class InputConsumer implements ListenerTemplate {

    public int input;
    private Object UnknownHostException;
    private Socket socket = null;
    private DataInputStream in = null;
    private DataOutputStream out = null;

    public void start() throws IOException {
        setupClient();
        //      SharedDataStructure.getSharedDataStructure().setup(2);

        //sharedQueue.setup(2);
        //System.out.println(sharedQueue.getIpc());

    }

    private void setupClient() throws IOException {


        try {
            socket = new Socket("localhost", 63811);
            System.out.println("Connected");

            // takes input from terminal
            in = new DataInputStream(System.in);

            // sends output to the socket
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException i) {
            System.out.println(i);
        }

        // string to read message from input
        String line = "";

        // keep reading until "Over" is input
        while (!line.equals("Over")) {
            try {
                line = in.readLine();
                out.writeUTF(line);
            } catch (IOException i) {
                System.out.println(i);
            }
        }

        // close the connection
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException i) {
            System.out.println(i);
        }
    }

    public PrimeNumber run(int input) throws IOException {
        boolean isPrime = evaluateIfPrime(input);
        PrimeNumber primeNumber = new PrimeNumber();
        primeNumber.setInput(input);
        primeNumber.setPrime(isPrime);
        System.out.println("The prime number checked in input consumer  " + primeNumber.getInput() + "   " + primeNumber.isPrime());
        writeToServer(in, out, input, isPrime);
        return primeNumber;
    }

    private void writeToServer(DataInputStream in, DataOutputStream out, int input, boolean isPrime) {
        try {
            System.out.println("Attempting to write to server");

            out.writeInt(input);
            out.writeBoolean(isPrime);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean evaluateIfPrime(int n) {
        if (n <= 1) return false; // 0, 1, negative numbers are not prime numbers by definition

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    @Override
    public PrimeNumber invoke(int input) throws IOException {
        return run(input);
    }


}
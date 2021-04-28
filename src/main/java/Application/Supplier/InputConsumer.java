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
    private static DataInputStream in = null;
    private static DataOutputStream out = null;

    public void start() throws IOException {
        setupClient();
    }

    private void setupClient() throws IOException {
        try {
            socket = new Socket("localhost", 63811);
            System.out.println("Connected");

            // takes input from terminal
            in = new DataInputStream(System.in);
            System.out.println(in.toString());
            // sends output to the socket
            out = new DataOutputStream(socket.getOutputStream());
            out.write(10);
            out.writeBoolean(true);
            System.out.println(out.toString());
        } catch (IOException i) {
            System.out.println(i);
        }

        // string to read message from input
        String line = "";

        // keep reading until "Over" is input
        while (true) {
            try {
                line = in.readLine();
                out.writeUTF(line);
            } catch (IOException i) {
                System.out.println(i);
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
    }

    public PrimeNumber run(int input) throws IOException {
        boolean isPrime = evaluateIfPrime(input);
        PrimeNumber primeNumber = new PrimeNumber();
        primeNumber.setInput(input);
        primeNumber.setPrime(isPrime);
        return primeNumber;
    }

    private void writeToServer(DataInputStream in, DataOutputStream out, int input, boolean isPrime) {
        try {
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

    public DataInputStream getIn() {
        return in;
    }

    public void setIn(DataInputStream in) {
        this.in = in;
    }

    public DataOutputStream getOut() {
        return out;
    }

    public void setOut(DataOutputStream out) {
        this.out = out;
    }
}
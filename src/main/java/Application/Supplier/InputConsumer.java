package Application.Supplier;

import Application.Util.PrimeNumber;
import Application.SharedDS.SharedDataStructure;

public class InputConsumer implements ListenerTemplate {

    public int input;

    public void start() {
        SharedDataStructure.getSharedDataStructure().setup(2);

        //sharedQueue.setup(2);
        //System.out.println(sharedQueue.getIpc());

    }


    public PrimeNumber run(int input) {
        boolean isPrime = evaluateIfPrime(input);
        PrimeNumber primeNumber = new PrimeNumber();
        primeNumber.setInput(input);
        primeNumber.setPrime(isPrime);
        //System.out.println("The prime number checked is  " + primeNumber.getInput()  + "   " + primeNumber.isPrime());
        return primeNumber;
    }

    private boolean evaluateIfPrime(int n) {
        if (n <= 1) return false; // 0, 1, negative numbers are not prime numbers by definition

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    @Override
    public PrimeNumber invoke(int input) {
        return run(input);
    }


}
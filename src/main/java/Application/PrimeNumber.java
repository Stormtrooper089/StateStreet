package Application;

public class PrimeNumber {
    private int input;
    private boolean isPrime;

    public int getInput() {
        return input;
    }

    public void setInput(int input) {
        this.input = input;
    }

    public boolean isPrime() {
        return isPrime;
    }

    public void setPrime(boolean prime) {
        isPrime = prime;
    }

    @Override
    public String toString() {
        return "PrimeNumber{" +
                "input=" + input +
                ", isPrime=" + isPrime +
                '}';
    }
}

package Application.Supplier;

import Application.Util.PrimeNumber;

import java.io.IOException;

public interface ListenerTemplate {
    public PrimeNumber invoke(int k) throws IOException;

}

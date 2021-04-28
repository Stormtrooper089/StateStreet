package Application.Producer;

import Application.Producer.Generator;

public class GeneratorRunner {
    public static void main(String[] args) throws InterruptedException {
        new Generator().run();
    }
}

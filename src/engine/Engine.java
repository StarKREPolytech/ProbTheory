package engine;

import annotations.Alphabet;
import annotations.Signal;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import supplier.Supplier;

import java.util.List;

@Singleton
public final class Engine {

    private String signalPath = "./src/input/inputSignals.txt";

    private String alphabetPath = "./src/input/alphabet.txt";

    @Inject
    @Signal
    private Supplier signalSupplier;

    @Inject
    @Alphabet
    private Supplier alphabetSupplier;

    public final void getSolution(){
        final List<String> alphabet = alphabetSupplier.load(alphabetPath);
        final List<String> signals = signalSupplier.load(signalPath);
        System.out.println(alphabet.size());
        System.out.println(signals.size());
    }
}

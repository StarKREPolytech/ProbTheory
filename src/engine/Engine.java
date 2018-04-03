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

    private static final double NOIZE = 0.151;

    @Inject
    @Signal
    private Supplier signalSupplier;

    @Inject
    @Alphabet
    private Supplier alphabetSupplier;

    public final void getSolution(){
        final List<String> alphabet = alphabetSupplier.load(alphabetPath);
        final List<String> signals = signalSupplier.load(signalPath);
    }

    private double getConditionProbability(final String signal, final String symbol){

    }
}

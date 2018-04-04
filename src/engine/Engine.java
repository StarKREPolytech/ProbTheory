package engine;

import annotations.Alphabet;
import annotations.MessageSignal;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import engine.Task.Task;
import exeptions.IncomparableSymbolsExeption;
import supplier.Supplier;
import supplier.packageSupplier.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Singleton
public final class Engine {

    private static final Logger LOG = Logger.getLogger(Engine.class.getName());

    @Inject
    @MessageSignal
    private Supplier<Message> signalMessageSupplier;

    @Inject
    @Alphabet
    private Supplier<String> alphabetSupplier;

    private String signalPath = "./src/input/inputSignals.txt";

    private String alphabetPath = "./src/input/alphabet.txt";

    private static final double NOIZE = 0.151;

    private List<Double> probDistribution;


    public final void getSolution(final Task task){
        final List<String> alphabet = alphabetSupplier.load(alphabetPath);
        final List<Message> messages = signalMessageSupplier.load(signalPath);
        switch (task){
            case UNIFORM:
                final int alphabetCapacity = alphabet.size();
                setUniformDistribution(alphabetCapacity);
                break;
            case INDIVIDUAL:
                setIndividualSolution();
        }
    }

    private void solve(final List<String> alphabet, final List<Message> messages){
        for (final Message message : messages){
            final List<String> letters = message.getLetters();
            for (final String letter : letters){
                final char[] inputBits = letter.toCharArray();

                final List<Double> probValuesOfBayes = new ArrayList<>();
                double probSum = 0;
                for (final String symbol : alphabet){
                    final char[] symbolBits = symbol.toCharArray();

                }
            }
        }
    }


    private void setUniformDistribution(final int alphabetCapacity){
        this.probDistribution = new ArrayList<>();
        final double alphabetProb = 1 / alphabetCapacity;
        for (int i = 0; i < alphabetCapacity; i++){
            this.probDistribution.add(alphabetProb);
        }
    }

    private void setIndividualSolution(){

    }

    private double countBitProb(final char[] inputBits, final char[] symbolBits) throws IncomparableSymbolsExeption {
        if (inputBits.length == symbolBits.length){
            for (int i = 0 ; i < inputBits.length; i++){
                if ()
                final int inputBit = inputBits[i];
                final int symbols = symbolBits[i];
                if ()
            }



            return
        } else {
            throw new IncomparableSymbolsExeption();
        }
    }

//    private double getConditionProbability(final String signal, final String symbol){
//
//    }
}

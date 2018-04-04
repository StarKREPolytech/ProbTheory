package engine;

import annotations.Alphabet;
import annotations.MessageSignal;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import engine.Task.Task;
import exeptions.IncomparableTypesExeption;
import supplier.Supplier;
import supplier.packageSupplier.Message;
import utils.Utils;

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

    static final double NOISE_PROB = 0.151;

    static final double CLEAR_PROB = 0.849;

    private List<Double> aprioriProb;

    private List<Double> aposterioriProb;


    public final void getSolution(final Task task) {
        final List<String> alphabet = alphabetSupplier.load(alphabetPath);
        final List<Message> messages = signalMessageSupplier.load(signalPath);
        switch (task) {
            case UNIFORM:
                final int alphabetCapacity = alphabet.size();
                setUniformDistribution(alphabetCapacity);
                break;
            case INDIVIDUAL:
                setIndividualSolution();
        }
    }

    private void solve(final List<String> alphabet, final List<Message> messages) throws IncomparableTypesExeption {
        for (final Message message : messages) {
            final List<String> letters = message.getLetters();
            for (final String letter : letters) {
                final char[] inputBits = Utils.convertToCharArray(letter);
                final List<Double> probCondValues = new ArrayList<>();
                final List<Double> probBayesList = new ArrayList<>();
                for (final String symbol : alphabet) {
                    final char[] symbolBits = Utils.convertToCharArray(symbol);
                    final double condProbability = ProbabilityFunctionsUtility.countBitProb(inputBits, symbolBits);
                    probCondValues.add(condProbability);
                }
                for (int i = 0; i < alphabet.size(); i++){
                    final double valueOfBayes = ProbabilityFunctionsUtility
                            .countByesProb(i, aprioriProb, probCondValues);
                    probBayesList.add(valueOfBayes);
                }
            }
        }
    }

    private void setUniformDistribution(final int alphabetCapacity) {
        this.aprioriProb = new ArrayList<>();
        final double alphabetProb = 1 / alphabetCapacity;
        for (int i = 0; i < alphabetCapacity; i++) {
            this.aprioriProb.add(alphabetProb);
        }
    }

    private void setIndividualSolution() {

    }


//    private double getConditionProbability(final String signal, final String symbol){
//
//    }
}

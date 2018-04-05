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

import java.util.*;
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

    private String signalPath = "./src/resources/input/inputSignals.txt";

    private String alphabetPath = "./src/resources/input/alphabet.txt";

    private String responsePath = "./src/resources/output/output.txt";

    static final double NOISE_PROB = 0.151;

    static final double CLEAR_PROB = 0.849;

    static final double RATIONING_COEFFICIENT = 200;

    private List<Double> aprioriProbList;

    private List<Double> aposterioriProbList;

    private double entropy;

    private double condEntropy;

    private double informationQuantity;


    public final void makeResponse(final Task task) {
        final List<String> alphabet = alphabetSupplier.load(alphabetPath);
        final List<Message> messages = signalMessageSupplier.load(signalPath);
        switch (task) {
            case UNIFORM:
                final int alphabetCapacity = alphabet.size();
                setUniformDistribution(alphabetCapacity);
                break;
            case INDIVIDUAL:
                setIndividualDistribution();
        }
        try {
            getMessages(alphabet, messages);
        } catch (final IncomparableTypesExeption incomparableTypesExeption) {
            incomparableTypesExeption.printStackTrace();
        }
    }

    //    private void countAposteriori(final List<String> alphabet, final List<Message> messages)
//            throws IncomparableTypesExeption {
//        for (final Message message : messages) {
//            final List<String> letters = message.getLetters();
//            final String firstLetter = letters.get(4);
//            final char[] inputBits = Utils.convertToCharArray(firstLetter);
//            final List<Double> condProbList = new ArrayList<>();
//            final List<Double> probBayesList = new ArrayList<>();
//            for (final String symbol : alphabet) {
//                final char[] symbolBits = Utils.convertToCharArray(symbol);
//                final double condProbability = ProbabilityFunctionsUtility.countBitProb(inputBits, symbolBits);
//                condProbList.add(condProbability);
//            }
//            for (int i = 0; i < alphabet.size(); i++) {
//                final double aposterioriProb = ProbabilityFunctionsUtility
//                        .countByesProb(i, aprioriProbList, condProbList);
//                probBayesList.add(aposterioriProb);
//            }
//            //output:
//            System.out.println("Message №" + message.getNumber() + ":" + "\nAposteriori probability:");
//            int j = 1;
//            for (final double response : probBayesList){
//                System.out.println("(" + j + ";" + response + ")");
//                j++;
//            }
//            int symbolNumber = 0;
//            double max = 0;
//            for (int i = 0; i < probBayesList.size(); i++){
//                if (probBayesList.get(i) > max){
//                    max = probBayesList.get(i);
//                    symbolNumber = i;
//                }
//            }
//            System.out.println("Symbol: " + symbolNumber);
//            this.aprioriProbList = probBayesList;
//        }
//    }
    private final List<Message> outputMessages = new ArrayList<>() {{
        for (int i = 0; i < 20; i++) {
            add(new Message(i + 1, new ArrayList<>()));
        }
    }};

    private void getMessages(final List<String> alphabet, final List<Message> messages)
            throws IncomparableTypesExeption {
        for (int x = 0; x < 229; x++) {
            setUniformDistribution(alphabet.size());
            for (int j = 0; j < messages.size(); j++) {
                final List<String> letters = messages.get(j).getLetters();
                final String firstLetter = letters.get(x);
                final char[] inputBits = Utils.convertToCharArray(firstLetter);
                final List<Double> condProbList = new ArrayList<>();
                final List<Double> probBayesList = new ArrayList<>();
                for (final String symbol : alphabet) {
                    final char[] symbolBits = Utils.convertToCharArray(symbol);
                    final double condProbability = ProbabilityFunctionsUtility.countBitProb(inputBits, symbolBits);
                    condProbList.add(condProbability);
                }
                for (int i = 0; i < alphabet.size(); i++) {
                    final double aposterioriProb = ProbabilityFunctionsUtility
                            .countByesProb(i, aprioriProbList, condProbList);
                    probBayesList.add(aposterioriProb);
                }
                double max = 0;
                int number = 0;
                for (int i = 0; i < probBayesList.size(); i++) {
                    final double value = probBayesList.get(i);
                    if (value > max) {
                        max = value;
                        number = i;
                    }
                }
                outputMessages.get(j).getLetters().add(map.get(number));
                this.aprioriProbList = probBayesList;
            }
        }
        for (final Message message : outputMessages) {
            final List<String> letters = message.getLetters();
            final StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < 229; i++ ){
//                if (i == 130){
//                    stringBuilder.append("\n");
//                }
                stringBuilder.append(letters.get(i));
            }
            System.out.println("Сообщение: " + message.getNumber());
            System.out.println(stringBuilder.toString());
            System.out.println("\n");
        }
    }

//    private void makeResponse(final List<String> alphabet, final List<Message> messages) throws IncomparableTypesExeption {
//        for (final Message message : messages) {
//            final List<String> letters = message.getLetters();
//            for (final String letter : letters) {
//                final char[] inputBits = Utils.convertToCharArray(letter);
//                final List<Double> condProbList = new ArrayList<>();
//                final List<Double> probBayesList = new ArrayList<>();
//                for (final String symbol : alphabet) {
//                    final char[] symbolBits = Utils.convertToCharArray(symbol);
//                    final double condProbability = ProbabilityFunctionsUtility.countBitProb(inputBits, symbolBits);
//                    condProbList.add(condProbability);
//                }
//                for (int i = 0; i < alphabet.size(); i++){
//                    final double aposterioriProb = ProbabilityFunctionsUtility
//                            .countByesProb(i, aprioriProbList, condProbList);
//                    probBayesList.add(aposterioriProb);
//                }
//                this.aposterioriProbList = probBayesList;
//                this.entropy = ProbabilityFunctionsUtility.countEntropy(aprioriProbList);
//                this.condEntropy = ProbabilityFunctionsUtility.countCondEntropy(aposterioriProbList);
//                this.informationQuantity = ProbabilityFunctionsUtility.countInformationQuantity(entropy, condEntropy);
//            }
//        }
//    }

    private void setUniformDistribution(final int alphabetCapacity) {
        this.aprioriProbList = new ArrayList<>();
        final double denominator = (double) alphabetCapacity;
        final double alphabetProb = 1 / denominator;
        for (int i = 0; i < alphabetCapacity; i++) {
            this.aprioriProbList.add(alphabetProb);
        }
    }

    private void setIndividualDistribution() {
        final List<Double> LETTER_FREQUENCY_LIST = Arrays.asList(0.007, 0.007, 0.006, 0.007, 0.007, 0.007, 0.007, 0.007
                , 0.007, 0.007, 8.66, 1.51, 4.19, 1.41, 2.56, 8.00, 0.24, 0.78, 1.81, 7.45, 1.31, 3.47, 4.32, 3.29, 6.35
                , 9.28, 3.35, 5.53, 5.45, 6.30, 2.90, 0.40, 0.92, 0.52, 1.27, 0.77, 0.49, 0.04, 2.11, 1.90, 0.17, 1.03
                , 2.22, 8.66, 1.51, 4.19, 1.41, 2.56, 8.00, 0.10, 0.78, 1.81, 7.45, 1.31, 3.47, 4.32, 3.29, 6.35
                , 9.28, 3.35, 5.53, 5.45, 6.30, 2.90, 0.40, 0.92, 0.52, 1.27, 0.77, 0.49, 0.04, 2.11, 1.90, 0.17, 1.03
                , 2.22, 0.007, 0.007, 0.007, 0.007, 0.0069, 0.00609, 0.006, 0.006, 0.006, 0.006, 0.006);
        this.aprioriProbList = new ArrayList<>() {{
            for (final double e : LETTER_FREQUENCY_LIST) {
                add(e / RATIONING_COEFFICIENT);
            }
        }};
    }


//    private double getConditionProbability(final String signal, final String symbol){
//
//    }


    private final Map<Integer, String> map = new HashMap<>() {{
        put(0, "0");
        put(1, "1");
        put(2, "2");
        put(3, "3");
        put(4, "4");
        put(5, "5");
        put(6, "6");
        put(7, "7");
        put(8, "8");
        put(9, "9");
        put(10, "А");
        put(11, "Б");
        put(12, "В");
        put(13, "Г");
        put(14, "Д");
        put(15, "Е");
        put(16, "Ё");
        put(17, "Ж");
        put(18, "З");
        put(19, "И");
        put(20, "Й");
        put(21, "К");
        put(22, "Л");
        put(23, "М");
        put(24, "Н");
        put(25, "О");
        put(26, "П");
        put(27, "Р");
        put(28, "С");
        put(29, "Т");
        put(30, "У");
        put(31, "Ф");
        put(32, "Х");
        put(33, "Ц");
        put(34, "Ч");
        put(35, "Ш");
        put(36, "Щ");
        put(37, "Ь");
        put(38, "Ы");
        put(39, "Ъ");
        put(40, "Э");
        put(41, "Ю");
        put(42, "Я");
        put(43, "а");
        put(44, "б");
        put(45, "в");
        put(46, "г");
        put(47, "д");
        put(48, "е");
        put(49, "ё");
        put(50, "ж");
        put(51, "з");
        put(52, "и");
        put(53, "й");
        put(54, "к");
        put(55, "л");
        put(56, "м");
        put(57, "н");
        put(58, "о");
        put(59, "п");
        put(60, "р");
        put(61, "с");
        put(62, "т");
        put(63, "у");
        put(64, "ф");
        put(65, "х");
        put(66, "ц");
        put(67, "ч");
        put(68, "ш");
        put(69, "щ");
        put(70, "ь");
        put(71, "ы");
        put(72, "ъ");
        put(73, "э");
        put(74, "ю");
        put(75, "я");
        put(76, ".");
        put(77, ",");
        put(78, "!");
        put(79, ":");
        put(80, "?");
        put(81, "-");
        put(82, "_");
        put(83, "№");
        put(84, "(");
        put(85, ")");
        put(86, " ");
    }};
}

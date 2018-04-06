package engine;

import exeptions.IncomparableTypesExeption;

import java.util.List;

import static engine.EnginePart1.CLEAR_PROB;
import static engine.EnginePart1.NOISE_PROB;

public final class ProbabilityFunctionsUtility {

    public static double countBitProb(final char[] inputBits
            , final char[] symbolBits) throws IncomparableTypesExeption {
        double product = 1;
        if (inputBits.length == symbolBits.length) {
            for (int i = 0; i < inputBits.length; i++) {
                if (inputBits[i] == symbolBits[i]) {
                    product *= CLEAR_PROB;
                } else {
                    product *= NOISE_PROB;
                }
            }
            return product;
        } else {
            throw new IncomparableTypesExeption();
        }
    }

    static double countByesProb(final int index, final List<Double> probList, final List<Double> condProbList)
            throws IncomparableTypesExeption {
        final double numerator;
        final double denominator;
        if (probList.size() == condProbList.size()){
            double sum = 0;
            for (int i = 0; i < probList.size(); i++){
                sum += probList.get(i) * condProbList.get(i);
            }
            numerator = probList.get(index) * condProbList.get(index);
            denominator = sum;
//            System.out.println("Numerator: " + numerator);
//            System.out.println("Denominator:" + denominator);
            return numerator / denominator;
        } else {
            throw new IncomparableTypesExeption();
        }
    }

    static double countEntropy(final List<Double> probArray){
        double sum = 0;
        for (final double prob : probArray){
            sum += prob * (Math.log10(prob) / Math.log10(2)); //log a at 2;
        }
        return (-1) * sum;
    }

    static double countCondEntropy(final List<Double> probByesArray){
        return countEntropy(probByesArray);
    }

    static double countInformationQuantity(final double entropy, final double condEntropy){
        return entropy - condEntropy;
    }

    static double countAverageInformationQuantityAboutLetter(final List<Double> probByesArray
            , final List<Double> probArray, final double condEntropy){
        double sum = 0;
        for (int i = 0; i < probByesArray.size(); i++){
            sum += probByesArray.get(i) * (Math.log10(probArray.get(i)) / Math.log10(2));
        }
        sum = sum * (-1);
        return sum - condEntropy;
    }

    static double countLetterProb(final List<Double> probArray, final List<Double> probCondArray){
        double sum = 0;
        for (int i = 0; i < probArray.size(); i++){
            sum += probArray.get(i) * probCondArray.get(i);
        }
        return sum;
    }

    static double countAverageCondEntropy(final List<Double> letterProbArray, final List<Double> condEntropyArray){
        double sum = 0;
        for (int i = 0; i < letterProbArray.size(); i++){
            sum += letterProbArray.get(i) * condEntropyArray.get(i);
        }
        return sum;
    }

    static double countAverageInfoAboutMessages(final List<Double> probLetterArray, final List<Double> infoLetterArray){
        double sum = 0;
        for (int i = 0; i < probLetterArray.size(); i++){
            sum += probLetterArray.get(i) * infoLetterArray.get(i);
        }
        return sum;
    }
}

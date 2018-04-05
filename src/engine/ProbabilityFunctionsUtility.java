package engine;

import exeptions.IncomparableTypesExeption;

import java.util.List;

import static engine.Engine.CLEAR_PROB;
import static engine.Engine.NOISE_PROB;

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

    static double countCondEntropy(final List<Double> condProbArray){
        return countEntropy(condProbArray);
    }

    static double countInformationQuantity(final double entropy, final double condEntropy){
        return entropy - condEntropy;
    }

    static double countAverageInformationQuantity(final List<Double> condProbArray
            , final List<Double> probArray, final double condEntropy){
        double sum = 0;
        for (int i = 0; i < condProbArray.size(); i++){
            sum += condProbArray.get(i) * (Math.log10(probArray.get(i)) / Math.log10(2));
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

    static double countAverageEntropy(final List<Double> letterProbArray, final List<Double> probByesArray){
        double sum = 0;

    }
}

package supplier.alphabet;

import supplier.Supplier;

import java.util.ArrayList;
import java.util.List;

public final class AlphabetSupplier implements Supplier<String> {

    private List<String> signals = new ArrayList<>();

    @Override
    public final List<String> parse(final List<String> strings) {
        final List<String> outputAlphabet = new ArrayList<>();
        for (final String string : strings){
            final String[] letters = string.split("\t");
            for (final String letter : letters){
                if (letter.length() == 7){
                    outputAlphabet.add(letter);
                }
            }
        }
        return outputAlphabet;
    }

    @Override
    public final List<String> getElements() {
        return signals;
    }

    @Override
    public final void setElements(final List<String> messages) {
        this.signals = messages;
    }
}
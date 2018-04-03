package supplier;

import com.google.inject.Singleton;

import java.util.ArrayList;
import java.util.List;

@Singleton
public final class SignalSupplier implements Supplier {

    private List<String> signals = new ArrayList<>();

    @Override
    public List<String> parse(final List<String> strings){
        final List<String> outputSignals = new ArrayList<>();
        for (final String string : strings){
            final String[] signals = string.split(" ");
            for (final String signal : signals){
                if (signal.length() != 0){
                    outputSignals.add(signal);
                }
            }
        }
        return outputSignals;
    }

    @Override
    public final List<String> getSignals() {
        return signals;
    }

    @Override
    public final void setSignals(List<String> signals) {
        this.signals = signals;
    }
}
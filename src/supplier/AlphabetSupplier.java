package supplier;

import java.util.ArrayList;
import java.util.List;

public final class AlphabetSupplier implements Supplier{

    private List<String> signals = new ArrayList<>();

    @Override
    public List<String> parse(final List<String> strings) {
        return null;
    }

    @Override
    public final List<String> getSignals() {
        return signals;
    }

    @Override
    public final void setSignals(final List<String> signals) {
        this.signals = signals;
    }
}
package module;

import annotations.Alphabet;
import annotations.Signal;
import com.google.inject.AbstractModule;
import supplier.AlphabetSupplier;
import supplier.SignalSupplier;
import supplier.Supplier;

public final class SupplierModule extends AbstractModule {

    @Override
    protected final void configure() {
        bind(Supplier.class).annotatedWith(Alphabet.class).to(AlphabetSupplier.class);
        bind(Supplier.class).annotatedWith(Signal.class).to(SignalSupplier.class);
    }
}

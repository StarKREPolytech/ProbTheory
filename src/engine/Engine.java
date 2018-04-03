package engine;

import annotations.Alphabet;
import annotations.Signal;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import supplier.Supplier;

@Singleton
public final class Engine {

    @Signal
    @Inject
    private Supplier signalSupplier;

    @Alphabet
    @Inject
    private Supplier alphabetSupplier;




}

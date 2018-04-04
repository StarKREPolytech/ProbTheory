package module;

import annotations.Alphabet;
import annotations.MessageSignal;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import supplier.alphabet.AlphabetSupplier;
import supplier.packageSupplier.Message;
import supplier.packageSupplier.MessageSupplier;
import supplier.Supplier;

public final class SupplierModule extends AbstractModule {

    @Override
    protected final void configure() {
        final TypeLiteral<Supplier<String>> alphabetLiteral = new TypeLiteral<>(){};
        final TypeLiteral<Supplier<Message>> messageLiteral = new TypeLiteral<>(){};
        bind(alphabetLiteral).annotatedWith(Alphabet.class).to(AlphabetSupplier.class);
        bind(messageLiteral).annotatedWith(MessageSignal.class).to((MessageSupplier.class));
    }
}

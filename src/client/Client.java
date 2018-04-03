package client;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import supplier.SignalSupplier;

import java.util.List;

public final class Client {

    @Inject
    private SignalSupplier signalSupplier;

    public static void main(String[] args) {
        final Injector injector = Guice.createInjector();
        final Client client = injector.getInstance(Client.class);
        final SignalSupplier signalSupplier = client.signalSupplier;
        final List<String> signals = signalSupplier.load("./src/input/inputSignals.txt");
        System.out.println(signals.toString());
    }

}

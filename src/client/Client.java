package client;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import engine.Engine;
import module.SupplierModule;

public final class Client {

    @Inject
    private Engine engine;

    public static void main(final String[] args) {
        final Injector injector = Guice.createInjector(new SupplierModule());
        final Client client = injector.getInstance(Client.class);
        final Engine engine = client.engine;
        engine.getSolution();
    }
}
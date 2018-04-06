package client;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import engine.EnginePart1;
import engine.EnginePart2;
import engine.Task.Task;
import module.SupplierModule;

public final class Client {

    @Inject
    private EnginePart1 enginePart1;

    @Inject
    private EnginePart2 enginePart2;

    public static void main(final String[] args) {
        final Injector injector = Guice.createInjector(new SupplierModule());
        final Client client = injector.getInstance(Client.class);
        final EnginePart2 enginePart2 = client.enginePart2;
        enginePart2.makeResponse(Task.UNIFORM);
    }
}
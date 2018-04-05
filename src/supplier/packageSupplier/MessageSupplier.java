package supplier.packageSupplier;

import supplier.Supplier;

import java.util.ArrayList;
import java.util.List;

public final class MessageSupplier implements Supplier<Message> {

    private List<Message> messages = new ArrayList<>();

    private static final int INFORMATION_INDEXES = 3;

    @Override
    public final List<Message> parse(final List<String> strings){
        int number = 1;
        for (final String string : strings){
            final List<String> signals = new ArrayList<>();
            final String[] substrings = string.split(" ");
            for (int i = 0; i < substrings.length; i++){
                final String e = substrings[i];
                if (e.length() == 7 && i > INFORMATION_INDEXES){
                    signals.add(e);
                }
            }
            final Message message = new Message(number, signals);
            messages.add(message);
            number++;
        }
        return messages;
    }

    @Override
    public final List<Message> getElements() {
        return messages;
    }

    @Override
    public final void setElements(final List<Message> messages) {
        this.messages = messages;
    }
}
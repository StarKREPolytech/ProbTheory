package supplier.packageSupplier;

import java.util.List;
import java.util.Objects;

public final class Message {

    private final int number;

    private final List<String> letters;

    public Message(final int number, final List<String> letters){
        this.number = number;
        this.letters = letters;
    }

    public final int getNumber() {
        return number;
    }

    public final List<String> getLetters() {
        return letters;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return number == message.number &&
                Objects.equals(letters, message.letters);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(number, letters);
    }

    @Override
    public final String toString() {
        return "\nMessageSignal{" +
                "number=" + number +
                ", letters=" + letters +
                '}';
    }
}
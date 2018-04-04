package supplier;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface Supplier<T> {

    default List<T> load(final String filePath) {
        if (getElements().isEmpty()){
            final File file = new File(filePath);
            final FileReader fileReader;
            try {
                fileReader = new FileReader(file);
                final BufferedReader bufferedReader = new BufferedReader(fileReader);
                final Stream<String> stringStream = bufferedReader.lines();
                final List<String> strings = stringStream.collect(Collectors.toList());
                setMessages(parse(strings));
            } catch (final FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return getElements();
    }

    default int getCapacity(){
        return getElements().size();
    }

    List<T> parse(final List<String> strings);

    List<T> getElements();

    void setMessages(final List<T> messages);
}

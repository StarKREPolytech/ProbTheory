package supplier;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface Supplier {

    default List<String> load(final String filePath) {
        if (getSignals().isEmpty()){
            final File file = new File(filePath);
            final FileReader fileReader;
            try {
                fileReader = new FileReader(file);
                final BufferedReader bufferedReader = new BufferedReader(fileReader);
                final Stream<String> stringStream = bufferedReader.lines();
                final List<String> strings = stringStream.collect(Collectors.toList());
                setSignals(parse(strings));
            } catch (final FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return getSignals();
    }

    List<String> parse(final List<String> strings);

    List<String> getSignals();

    void setSignals(final List<String> signals);
}

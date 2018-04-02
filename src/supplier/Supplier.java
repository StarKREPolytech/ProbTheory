package supplier;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Singleton
public final class Supplier {

    private String path;

    private List<String> signals;

    public final List<String> load(){
        if (signals.isEmpty()){
            final File file = new File(path);
            final FileReader fileReader;
            try {
                fileReader = new FileReader(file);
                final BufferedReader bufferedReader = new BufferedReader(fileReader);
                final Stream<String> stringStream = bufferedReader.lines();
                final List<String> strings = stringStream.collect(Collectors.toList());
                signals = parseSignals(strings);
            } catch (final FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return signals;
    }

    private List<String> parseSignals(final List<String> strings){
        final List<String> outputSignals = new ArrayList<>();
        for (final String string : strings){
            final String[] signals = string.split(" ");
            for (final String signal : signals){
                if (signal.length() != 0){
                    outputSignals.add(signal);
                }
            }
        }
        return outputSignals;
    }

    @Inject
    public final void setPath(final String path){
        this.path = path;
    }
}
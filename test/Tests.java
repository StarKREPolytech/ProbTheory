import engine.ProbabilityFunctionsUtility;
import exeptions.IncomparableTypesExeption;
import org.junit.jupiter.api.Test;
import utils.Utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

final class Tests {




    @Test
    final void countBitProb() {
        final char[] inputBits = Utils.convertToCharArray("10101");
        final char[] otherBits = Utils.convertToCharArray("11101");
        try {
            assertEquals(0.07845266632175098, ProbabilityFunctionsUtility.countBitProb(inputBits, otherBits));
        } catch (final IncomparableTypesExeption incomparableTypesExeption) {
            incomparableTypesExeption.printStackTrace();
        }
    }




}

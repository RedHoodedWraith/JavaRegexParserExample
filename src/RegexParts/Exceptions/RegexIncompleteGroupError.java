package RegexParts.Exceptions;

import java.util.Arrays;

public class RegexIncompleteGroupError extends RegexSyntaxError {

    public RegexIncompleteGroupError(char[] fullPatt) {
        super("Incomplete Brackets. "+ "\n\t" +
                "Full Pattern: " + Arrays.toString(fullPatt));
    }

}

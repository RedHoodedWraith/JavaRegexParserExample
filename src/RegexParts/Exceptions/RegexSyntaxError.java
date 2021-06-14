package RegexParts.Exceptions;

import java.util.Arrays;

public class RegexSyntaxError extends RegexOtherError {

    public RegexSyntaxError(char currentToken, int index, char[] remainingTokens, char[] fullPatt) {
        super("REGEX SYNTAX ERROR", "Invalid Pattern Next Token Found.\n\t" +
                "Current Token: " + currentToken + "\n\t" +
                "Current Index: " + index + "\n\t" +
                "Remaining Pattern: " + Arrays.toString(remainingTokens) + "\n\t" +
                "Full Pattern: " + Arrays.toString(fullPatt));
    }
}

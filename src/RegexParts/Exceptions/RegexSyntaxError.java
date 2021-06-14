package RegexParts.Exceptions;

import java.util.Arrays;

public class RegexSyntaxError extends RegexOtherError {

    public RegexSyntaxError(char currentToken, char[] remainingTokens) {
        super("REGEX SYNTAX ERROR", "Invalid Pattern Next Token Found.\n\t" +
                "Current Token: " + currentToken + "\n\t" +
                "Remaining Pattern: " + Arrays.toString(remainingTokens));
    }
}

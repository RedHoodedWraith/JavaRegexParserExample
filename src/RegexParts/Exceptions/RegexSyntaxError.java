package RegexParts.Exceptions;

import java.util.Arrays;

public class RegexSyntaxError extends RegexOtherError {

    private static final String ERROR_TITLE = "REGEX SYNTAX ERROR";

    public RegexSyntaxError(char currentToken, int index, char[] remainingTokens, char[] fullPatt) {
        super(ERROR_TITLE, "Invalid Pattern Next Token Found.\n\t" +
                "Current Token: " + currentToken + "\n\t" +
                "Current Index: " + index + "\n\t" +
                "Remaining Pattern: " + Arrays.toString(remainingTokens) + "\n\t" +
                "Full Pattern: " + Arrays.toString(fullPatt));
    }

    protected RegexSyntaxError(String title, String message) {
        super(title, message);
    }

    protected RegexSyntaxError(String message) {
        super(ERROR_TITLE, message);
    }
}

package RegexParts.Exceptions;

public class InvalidNextCharacterPatternException extends Exception {
    public InvalidNextCharacterPatternException(char currentToken, char[] remainingTokens) {
        super("");
    }
}

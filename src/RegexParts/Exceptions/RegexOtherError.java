package RegexParts.Exceptions;

public class RegexOtherError extends Exception {

    private static final String ERROR_MESSAGE_PREFIX = "REGEX ERROR";

    public RegexOtherError(String title, String message) {
        super(title + ": " + message);
    }

    public RegexOtherError(String message) {
        this(ERROR_MESSAGE_PREFIX, message);
    }
}

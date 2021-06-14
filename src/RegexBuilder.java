import RegexParts.Exceptions.InvalidNextCharacterPatternException;
import RegexParts.RegexElement;

public class RegexBuilder {

    public static RegexElement buildRegex(String regexPatternStr) throws InvalidNextCharacterPatternException {
        char[] regexPattern = regexPatternStr.toCharArray();
        int patternSize = regexPattern.length;
        return RegexElement.buildRegexElement(regexPattern, 0);
    }

    public static void main(String... args) {

    }

}

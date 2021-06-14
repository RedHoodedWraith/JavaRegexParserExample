import RegexParts.RegexElement;

public class RegexBuilder {

    public static RegexElement buildRegex(String regexPatternStr){
        char[] regexPattern = regexPatternStr.toCharArray();
        int patternSize = regexPattern.length;
        return RegexElement.buildRegexElement(regexPattern, 0);
    }

    

}

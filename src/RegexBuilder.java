public class RegexBuilder {

    private final char[] regexPattern;
    private final int patternSize;
    private int index = 0;

    public RegexBuilder(String regexPatternStr){
        this.regexPattern = regexPatternStr.toCharArray();
        this.patternSize = this.regexPattern.length;
    }

    public char[] getRegexPattern() {
        return regexPattern;
    }

    public char getCharFromPattern(int i) {
        return regexPattern[i];
    }

    public int getPatternSize() {
        return patternSize;
    }

}

public class LiteralCharacter extends RegexCharacter {

    protected LiteralCharacter() {
        // Ignore this token, required it to satisfy constructor requirements
        super('a');
    }

    public static boolean isLiteralCharacter(char c) {
        return !SPECIAL_CHARACTERS_LIST.contains(c);
    }

    @Override
    public boolean isTokenChar(char c) {
        return isLiteralCharacter(c);
    }

    @Override
    public boolean checkValidRegexSyntax(char[] patt, int index) {
        return false;
    }
}

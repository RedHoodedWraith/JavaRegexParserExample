public class AnyCharacter extends RegexCharacter {

    protected AnyCharacter() {
        super('.');
    }

    @Override
    public boolean checkValidRegexSyntax(char[] patt, int index) {
        return false;
    }
}

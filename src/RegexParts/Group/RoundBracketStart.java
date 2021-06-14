package RegexParts.Group;

public class RoundBracketStart extends RoundBracketTemplate {

    public RoundBracketStart(char[] patt, int index) {
        super(patt, index, '(', true, false, '.', '(', ')');
    }

}

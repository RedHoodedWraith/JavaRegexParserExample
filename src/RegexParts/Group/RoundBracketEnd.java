package RegexParts.Group;

public class RoundBracketEnd extends RoundBracketTemplate {

    public RoundBracketEnd(char[] patt, int index) {
        super(patt, index, ')', true, true, '.', '|', '*', '(', ')');
    }

}

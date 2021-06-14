package RegexParts.Group;

import RegexParts.Exceptions.RegexSyntaxError;

public class RoundBracketEnd extends RoundBracketTemplate {

    public RoundBracketEnd(char[] patt, int index, int groupLayer) throws RegexSyntaxError {
        super(patt, index, --groupLayer,')', true, true, '.', '|', '*', '(', ')');
    }

}

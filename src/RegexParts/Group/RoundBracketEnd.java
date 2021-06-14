package RegexParts.Group;

import RegexParts.Exceptions.RegexSyntaxError;

public class RoundBracketEnd extends RoundBracketTemplate {

    public RoundBracketEnd(char[] patt, int index) throws RegexSyntaxError {
        super(patt, index, ')', true, true, '.', '|', '*', '(', ')');
    }

}

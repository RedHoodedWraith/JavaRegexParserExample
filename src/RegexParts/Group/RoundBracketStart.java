package RegexParts.Group;

import RegexParts.Exceptions.RegexSyntaxError;

public class RoundBracketStart extends RoundBracketTemplate {

    public RoundBracketStart(char[] patt, int index) throws RegexSyntaxError {
        super(patt, index, '(', true, false, '.', '(', ')');
    }

}

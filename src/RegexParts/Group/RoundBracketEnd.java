package RegexParts.Group;

import RegexParts.Exceptions.InvalidNextCharacterPatternException;

public class RoundBracketEnd extends RoundBracketTemplate {

    public RoundBracketEnd(char[] patt, int index) throws InvalidNextCharacterPatternException {
        super(patt, index, ')', true, true, '.', '|', '*', '(', ')');
    }

}

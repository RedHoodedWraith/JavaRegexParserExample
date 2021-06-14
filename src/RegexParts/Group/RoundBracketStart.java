package RegexParts.Group;

import RegexParts.Exceptions.InvalidNextCharacterPatternException;

public class RoundBracketStart extends RoundBracketTemplate {

    public RoundBracketStart(char[] patt, int index) throws InvalidNextCharacterPatternException {
        super(patt, index, '(', true, false, '.', '(', ')');
    }

}

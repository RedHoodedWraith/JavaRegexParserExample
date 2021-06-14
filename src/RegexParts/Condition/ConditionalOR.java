package RegexParts.Condition;

import RegexParts.Exceptions.InvalidNextCharacterPatternException;
import RegexParts.RegexElement;

public class ConditionalOR extends RegexElement {

    public ConditionalOR(char[] patt, int index) throws InvalidNextCharacterPatternException {
        super(patt, index, '|', true, false, '.', '(');
    }

}

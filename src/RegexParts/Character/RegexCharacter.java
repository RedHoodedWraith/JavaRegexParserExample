package RegexParts.Character;

import RegexParts.Exceptions.InvalidNextCharacterPatternException;
import RegexParts.RegexElement;

public abstract class RegexCharacter extends RegexElement {

    protected RegexCharacter(char[] patt, int index, char tokenChar) throws InvalidNextCharacterPatternException {
        super(patt, index, tokenChar, true, true, RegexElement.SPECIAL_CHARACTERS);
    }

}

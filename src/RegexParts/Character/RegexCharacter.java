package RegexParts.Character;

import RegexParts.Exceptions.RegexSyntaxError;
import RegexParts.RegexElement;

public abstract class RegexCharacter extends RegexElement {

    protected RegexCharacter(char[] patt, int index, int groupLayer, char tokenChar) throws RegexSyntaxError {
        super(patt, index, groupLayer, tokenChar, true, true, RegexElement.SPECIAL_CHARACTERS);
    }

}

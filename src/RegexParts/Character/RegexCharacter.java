package RegexParts.Character;

import RegexParts.RegexElement;

public abstract class RegexCharacter extends RegexElement {

    protected RegexCharacter(char[] patt, int index, char tokenChar) {
        super(patt, index, tokenChar, true, true, RegexElement.SPECIAL_CHARACTERS);
    }

}

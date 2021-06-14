package RegexParts.Character;

import RegexParts.RegexElement;

public abstract class RegexCharacter extends RegexElement {

    protected RegexCharacter(char tokenChar) {
        super(tokenChar, true, true, RegexElement.SPECIAL_CHARACTERS);
    }

}

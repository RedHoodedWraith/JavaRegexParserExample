package RegexParts.Character;

import RegexParts.RegexElement;

public class LiteralCharacter extends RegexCharacter {

    protected LiteralCharacter(char c) {
        super(c);
    }

    public static boolean isLiteralCharacter(char c) {
        return !RegexElement.SPECIAL_CHARACTERS_LIST.contains(c);
    }

    @Override
    public boolean isTokenChar(char c) {
        return isLiteralCharacter(c) && super.isTokenChar(c);
    }

}

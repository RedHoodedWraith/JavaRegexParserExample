package RegexParts.Character;

import RegexParts.Exceptions.InvalidNextCharacterPatternException;
import RegexParts.RegexElement;

public class LiteralCharacter extends RegexCharacter {

    public LiteralCharacter(char[] patt, int index, char c) throws InvalidNextCharacterPatternException {
        super(patt, index, c);
    }

    public static boolean isLiteralCharacter(char c) {
        return !RegexElement.SPECIAL_CHARACTERS_LIST.contains(c);
    }

    @Override
    public boolean isTokenChar(char c) {
        return isLiteralCharacter(c) && super.isTokenChar(c);
    }

}

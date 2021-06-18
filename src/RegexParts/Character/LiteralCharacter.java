package RegexParts.Character;

import RegexParts.Exceptions.RegexSyntaxError;
import RegexParts.RegexElement;

import java.util.Arrays;

public class LiteralCharacter extends RegexCharacter {

    public LiteralCharacter(char[] patt, int index, int groupLayer, char c) throws RegexSyntaxError {
        super(patt, index, groupLayer, c);
    }

    public static boolean isLiteralCharacter(char c) {
        return !RegexElement.SPECIAL_CHARACTERS_LIST.contains(c);
    }

    @Override
    public boolean isTokenChar(char c) {
        return isLiteralCharacter(c) && super.isTokenChar(c);
    }

    @Override
    protected boolean isValidToken(char c) {
        return isTokenChar(c);
    }
}

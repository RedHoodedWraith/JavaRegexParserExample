package RegexParts.Character;

import RegexParts.Exceptions.RegexSyntaxError;
import RegexParts.Group.RoundBracketStart;

import java.util.Arrays;

public class AnyCharacter extends RegexCharacter {

    public AnyCharacter(char[] patt, int index, int groupLayer, RoundBracketStart groupStart) throws RegexSyntaxError {
        super(patt, index, groupLayer,'.', groupStart);
    }

    @Override
    protected boolean isValidToken(char c) {
        return true;
    }
}

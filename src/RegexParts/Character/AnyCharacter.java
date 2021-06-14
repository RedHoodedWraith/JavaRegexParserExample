package RegexParts.Character;

import RegexParts.Exceptions.RegexSyntaxError;

public class AnyCharacter extends RegexCharacter {

    public AnyCharacter(char[] patt, int index, int groupLayer) throws RegexSyntaxError {
        super(patt, index, groupLayer,'.');
    }

}

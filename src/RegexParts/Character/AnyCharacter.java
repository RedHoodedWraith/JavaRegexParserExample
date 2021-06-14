package RegexParts.Character;

import RegexParts.Exceptions.InvalidNextCharacterPatternException;

public class AnyCharacter extends RegexCharacter {

    public AnyCharacter(char[] patt, int index) throws InvalidNextCharacterPatternException {
        super(patt, index, '.');
    }

}

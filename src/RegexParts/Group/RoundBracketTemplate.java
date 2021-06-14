package RegexParts.Group;

import RegexParts.Exceptions.InvalidNextCharacterPatternException;
import RegexParts.RegexElement;

public abstract class RoundBracketTemplate extends RegexElement {

    protected RoundBracketTemplate(char[] patt, int index, char tokenChar, boolean nextTokenCanBeLiteral, boolean nextTokenCanBeEnd, Character... validNextTokensRaw) throws InvalidNextCharacterPatternException {
        super(patt, index, tokenChar, nextTokenCanBeLiteral, nextTokenCanBeEnd, validNextTokensRaw);
    }
}

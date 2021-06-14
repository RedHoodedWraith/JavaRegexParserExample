package RegexParts.Group;

import RegexParts.RegexElement;

public abstract class RoundBracketTemplate extends RegexElement {

    protected RoundBracketTemplate(char tokenChar, boolean nextTokenCanBeLiteral, boolean nextTokenCanBeEnd, Character... validNextTokensRaw) {
        super(tokenChar, nextTokenCanBeLiteral, nextTokenCanBeEnd, validNextTokensRaw);
    }
}

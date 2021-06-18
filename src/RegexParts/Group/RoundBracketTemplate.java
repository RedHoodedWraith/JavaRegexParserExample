package RegexParts.Group;

import RegexParts.Exceptions.RegexSyntaxError;
import RegexParts.RegexElement;

public abstract class RoundBracketTemplate extends RegexElement {

    protected RoundBracketTemplate(char[] patt, int index, int groupLayer, char tokenChar, boolean nextTokenCanBeLiteral, boolean nextTokenCanBeEnd, RoundBracketStart groupStart, Character... validNextTokensRaw) throws RegexSyntaxError {
        super(patt, index, groupLayer, tokenChar, nextTokenCanBeLiteral, nextTokenCanBeEnd,
                groupStart, validNextTokensRaw);
    }
}

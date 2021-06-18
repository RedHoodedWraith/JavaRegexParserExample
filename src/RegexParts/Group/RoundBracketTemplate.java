package RegexParts.Group;

import RegexParts.Exceptions.RegexSyntaxError;
import RegexParts.RegexElement;

import java.util.ArrayList;

public abstract class RoundBracketTemplate extends RegexElement {

    protected RoundBracketTemplate(char[] patt, int index, int groupLayer, char tokenChar,
                                   boolean nextTokenCanBeLiteral, boolean nextTokenCanBeEnd,
                                   ArrayList<RoundBracketStart> groupStartList, Character... validNextTokensRaw)
            throws RegexSyntaxError {
        super(patt, index, groupLayer, tokenChar, nextTokenCanBeLiteral, nextTokenCanBeEnd,
                groupStartList, validNextTokensRaw);
    }
}

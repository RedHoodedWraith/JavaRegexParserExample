package RegexParts.Group;

import RegexParts.Exceptions.RegexSyntaxError;

import java.util.ArrayList;

public class RoundBracketEnd extends RoundBracketTemplate {

    public RoundBracketEnd(char[] patt, int index, int groupLayer, ArrayList<RoundBracketStart> groupStartList) throws RegexSyntaxError {
        super(patt, index, --groupLayer,')', true, true,
                groupStartList, '.', '|', '*', '(', ')');
    }

    @Override
    public boolean evaluate(char[] inputTarget, int index) {
        return false;
    }
}

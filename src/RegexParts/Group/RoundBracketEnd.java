package RegexParts.Group;

import RegexParts.Exceptions.RegexSyntaxError;

public class RoundBracketEnd extends RoundBracketTemplate {

    public RoundBracketEnd(char[] patt, int index, int groupLayer, RoundBracketStart groupStart) throws RegexSyntaxError {
        super(patt, index, --groupLayer,')', true, true,
                groupStart, '.', '|', '*', '(', ')');
    }

    @Override
    public boolean evaluate(char[] inputTarget, int index) {
        return false;
    }
}

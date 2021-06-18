package RegexParts.Quantifier;

import RegexParts.Exceptions.RegexSyntaxError;
import RegexParts.Group.RoundBracketStart;
import RegexParts.RegexElement;

public class ZeroOrMore extends RegexElement {

    public ZeroOrMore(char[] patt, int index, int groupLayer, RoundBracketStart groupStart) throws RegexSyntaxError {
        super(patt, index, groupLayer, '*', true, true,
                groupStart, '.', '|', '(', ')');
    }

    @Override
    public boolean evaluate(char[] inputTarget, int index) {
        return false;
    }
}

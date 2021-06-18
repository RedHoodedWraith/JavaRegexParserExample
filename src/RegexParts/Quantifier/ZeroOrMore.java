package RegexParts.Quantifier;

import RegexParts.Exceptions.RegexSyntaxError;
import RegexParts.Group.RoundBracketStart;
import RegexParts.RegexElement;

import java.util.ArrayList;

public class ZeroOrMore extends RegexElement {

    public ZeroOrMore(char[] patt, int index, int groupLayer) throws RegexSyntaxError {
        super(patt, index, groupLayer, '*', true, true,
                '.', '|', '(', ')');
    }

    @Override
    public int evaluate(char[] inputTarget, int index) {
        return -1;
    }
}

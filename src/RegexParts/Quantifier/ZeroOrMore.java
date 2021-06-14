package RegexParts.Quantifier;

import RegexParts.Exceptions.RegexSyntaxError;
import RegexParts.RegexElement;

public class ZeroOrMore extends RegexElement {

    public ZeroOrMore(char[] patt, int index, int groupLayer) throws RegexSyntaxError {
        super(patt, index, groupLayer, '*', true, true, '.', '|', '(', ')');
    }

    @Override
    public boolean evaluate(char[] inputTarget, int index) {
        return false;
    }
}

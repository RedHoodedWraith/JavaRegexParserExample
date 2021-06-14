package RegexParts.Quantifier;

import RegexParts.Exceptions.RegexSyntaxError;
import RegexParts.RegexElement;

public class ZeroOrMore extends RegexElement {

    public ZeroOrMore(char[] patt, int index) throws RegexSyntaxError {
        super(patt, index, '*', true, true, '.', '|', '(', ')');
    }

}

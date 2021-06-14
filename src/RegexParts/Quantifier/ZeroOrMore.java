package RegexParts.Quantifier;

import RegexParts.RegexElement;

public class ZeroOrMore extends RegexElement {

    public ZeroOrMore(char[] patt, int index) {
        super(patt, index, '*', true, true, '.', '|', '(', ')');
    }

}

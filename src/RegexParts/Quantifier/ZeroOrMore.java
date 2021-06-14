package RegexParts.Quantifier;

import RegexParts.RegexElement;

public class ZeroOrMore extends RegexElement {

    protected ZeroOrMore() {
        super('*', true, true, '.', '|', '(', ')');
    }

}

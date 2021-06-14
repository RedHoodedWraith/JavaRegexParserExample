package RegexParts.Condition;

import RegexParts.RegexElement;

public class ConditionalOR extends RegexElement {

    public ConditionalOR(char[] patt, int index) {
        super(patt, index, '|', true, false, '.', '(');
    }

}

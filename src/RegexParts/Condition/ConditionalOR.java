package RegexParts.Condition;

import RegexParts.Exceptions.RegexSyntaxError;
import RegexParts.RegexElement;

public class ConditionalOR extends RegexElement {

    public ConditionalOR(char[] patt, int index) throws RegexSyntaxError {
        super(patt, index, '|', true, true, '.', '|', '(');
    }

}

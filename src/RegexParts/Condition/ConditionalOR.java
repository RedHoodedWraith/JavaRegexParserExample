package RegexParts.Condition;

import RegexParts.RegexElement;

public class ConditionalOR extends RegexElement {

    protected ConditionalOR() {
        super('|', true, false, '.', '(');
    }

}

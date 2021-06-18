package RegexParts.Condition;

import RegexParts.Exceptions.RegexSyntaxError;
import RegexParts.RegexElement;

public class ConditionalOR extends RegexElement {

    public ConditionalOR(char[] patt, int index, int groupLayer) throws RegexSyntaxError {
        super(patt, index, groupLayer,'|', true, true,
                '.', '|', '(');
    }

    @Override
    public int evaluate(char[] inputTarget, int index, boolean resultFromPreviousElement) {
        return evaluateTargetWithNextElement(inputTarget, index, resultFromPreviousElement);
    }
}

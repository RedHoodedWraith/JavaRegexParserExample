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
        if(inputTarget.length == 0 &&
                (isNextElementNull() || isFirstElement() || getNextElement() instanceof ConditionalOR)) {
            return 0;
        }
        else if(resultFromPreviousElement) {
            if(inputTarget.length-index <= 0)

            return index;
        }

        // Reattempt Target Evaluation with remainder of Pattern Elements
        return evaluateTargetWithNextElement(inputTarget, 0, false);
    }
}

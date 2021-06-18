package RegexParts.Character;

import RegexParts.Condition.ConditionalOR;
import RegexParts.Exceptions.RegexSyntaxError;
import RegexParts.RegexElement;

public abstract class RegexCharacter extends RegexElement {

    protected RegexCharacter(char[] patt, int index, int groupLayer, char tokenChar) throws RegexSyntaxError {
        super(patt, index, groupLayer, tokenChar, true, true, RegexElement.SPECIAL_CHARACTERS);
    }

    protected abstract boolean isValidToken(char c);

    @Override
    public int evaluate(char[] inputTarget, int index, boolean resultFromPreviousElement) {
        int iTLength = inputTarget.length;

        // Returns FAIL_INDEX_VALUE if Target Array is empty (length is zero)
        // OR if index entered is equal or greater than the Target Array length
        if(iTLength <= 0 || index >= iTLength) {
            return FAIL_INDEX_VALUE;
        }

        char c = inputTarget[index];

        // If Current Token is the Desired Literal Character or Any Literal Character
        // (dependent on sub class)
        if(isValidToken(c)) {

            // If There is a Next Pattern Element
            if(!isNextElementNull()) {
                // Check Next Element with next Target Token with true/regex passed state
                return evaluateNextTargetWithNextElement(inputTarget, index);
            }
            // Returns with index If This is the final Target Token
            else if(isFinalTargetChar(inputTarget, index)) {
                return index;
            }

        }
        // If evaluation failed and next element is Conditional
        else if(!isNextElementNull() && getNextElement() instanceof ConditionalOR) {
            return evaluateTargetWithNextElement(inputTarget, index, false);
        }




        // Returns FAIL_INDEX_VALUE if None of the conditions above were met
        return FAIL_INDEX_VALUE;
    }
}

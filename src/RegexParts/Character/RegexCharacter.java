package RegexParts.Character;

import RegexParts.Exceptions.RegexSyntaxError;
import RegexParts.Group.RoundBracketStart;
import RegexParts.RegexElement;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class RegexCharacter extends RegexElement {

    protected RegexCharacter(char[] patt, int index, int groupLayer, char tokenChar) throws RegexSyntaxError {
        super(patt, index, groupLayer, tokenChar, true, true, RegexElement.SPECIAL_CHARACTERS);
    }

    protected abstract boolean isValidToken(char c);

    @Override
    public int evaluate(char[] inputTarget, int index) {
        int iTLength = inputTarget.length;

        // Returns FAIL_INDEX_VALUE if Target Array is empty (length is zero)
        if(iTLength <= 0) {
            return FAIL_INDEX_VALUE;
        }

        // Throws an exception if an index entered is equal or greater than the Target Array length
        if(index >= iTLength) {
            throw new NullPointerException("Invalid Index: " + index +
                    "\nTarget Length: " + iTLength +
                    "\nTarget: " + Arrays.toString(inputTarget));
        }

        char c = inputTarget[index];

        // If Current Token is the Desired Literal Character or Any Literal Character
        // (dependent on sub class)
        if(isValidToken(c)) {

            // If Next Target Token is End
            if(isNextTargetCharEnd(inputTarget, index)) {
                // Returns current index if the next Target Token can the end
                return canNextTargetTokenBeEnd() ? index : FAIL_INDEX_VALUE;
            }
            // If Next Pattern Token is Not End (If Next Token is Not Null)
            else if(!isNextElementEnd()) {
                // Check Next Element with next Target Token
                return evaluateNextTargetWithNextElement(inputTarget, index);
            }
        }

        // Returns FAIL_INDEX_VALUE if None of the conditions above were met
        return FAIL_INDEX_VALUE;
    }
}

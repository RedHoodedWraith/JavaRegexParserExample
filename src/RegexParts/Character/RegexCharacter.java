package RegexParts.Character;

import RegexParts.Exceptions.RegexSyntaxError;
import RegexParts.Group.RoundBracketStart;
import RegexParts.RegexElement;

import java.util.Arrays;

public abstract class RegexCharacter extends RegexElement {

    protected RegexCharacter(char[] patt, int index, int groupLayer, char tokenChar, RoundBracketStart groupStart) throws RegexSyntaxError {
        super(patt, index, groupLayer, tokenChar, true, true, groupStart, RegexElement.SPECIAL_CHARACTERS);
    }

    protected abstract boolean isValidToken(char c);

    @Override
    public boolean evaluate(char[] inputTarget, int index) {
        int iTLength = inputTarget.length;

        // Returns false if Target Array is empty (length is zero)
        if(iTLength <= 0) {
            return false;
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
                // Returns true if the next Target Token can the end
                return canNextTargetTokenBeEnd();
            }
            // If Next Pattern Token is Not End (If Next Token is Not Null)
            else if(!isNextElementEnd()) {
                // Check Next Element with next Target Token
                return this.getNextElement().evaluate(inputTarget, index + 1);
            }
        }

        // Returns False if None of the conditions above were met
        return false;
    }
}

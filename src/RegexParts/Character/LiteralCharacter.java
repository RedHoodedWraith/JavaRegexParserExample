package RegexParts.Character;

import RegexParts.Exceptions.RegexSyntaxError;
import RegexParts.RegexElement;

import java.util.Arrays;

public class LiteralCharacter extends RegexCharacter {

    public LiteralCharacter(char[] patt, int index, int groupLayer, char c) throws RegexSyntaxError {
        super(patt, index, groupLayer, c);
    }

    public static boolean isLiteralCharacter(char c) {
        return !RegexElement.SPECIAL_CHARACTERS_LIST.contains(c);
    }

    @Override
    public boolean isTokenChar(char c) {
        return isLiteralCharacter(c) && super.isTokenChar(c);
    }

    @Override
    public boolean evaluate(char[] inputTarget, int index) {
        int iTLength = inputTarget.length;

        // Returns false if Target Array is empty (length is zero)
        if(iTLength <= 0) {
            return false;
        }

        if(index >= iTLength) {
            throw new NullPointerException("Invalid Index: " + index +
                    "\nTarget Length: " + iTLength +
                    "\nTarget: " + Arrays.toString(inputTarget));
        }

        char c = inputTarget[index];

        // If Current Token is the Desired Literal Character
        if(isTokenChar(c)) {

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

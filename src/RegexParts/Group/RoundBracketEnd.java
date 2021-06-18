package RegexParts.Group;

import RegexParts.Exceptions.RegexSyntaxError;
import RegexParts.Quantifier.ZeroOrMore;

public class RoundBracketEnd extends RoundBracketTemplate {

    private RoundBracketStart startOfCurrentGroup;

    public RoundBracketEnd(char[] patt, int index, int groupLayer) throws RegexSyntaxError {
        super(patt, index, --groupLayer,')', true, true,
                '.', '|', '*', '(', ')');
    }

    private boolean isNextTargetRepeatable() {
        return getNextElement() instanceof ZeroOrMore;
    }

    public RoundBracketStart getStartOfCurrentGroup() {
        return startOfCurrentGroup;
    }

    public void setStartOfCurrentGroup(RoundBracketStart startOfCurrentGroup) {
        this.startOfCurrentGroup = startOfCurrentGroup;
    }

    @Override
    public int evaluate(char[] inputTarget, int index, boolean resultFromPreviousElement) {

        // Repeats if Next in Pattern is Zero or More
        int updatedIndex = isNextTargetRepeatable() ?
                evaluateNextTargetWithElement(this.getStartOfCurrentGroup(), inputTarget, index, resultFromPreviousElement) : index;

        // Use current index value if post recursion value is FAIL_INDEX_VALUE
        updatedIndex = updatedIndex != FAIL_INDEX_VALUE ? updatedIndex : index;

        // If There is a Next Pattern Element
        if(!isNextElementNull()) {
            // If Recursive Group Search Succeeded
            if(updatedIndex < 0)
                // Check Next Element with next Target Token with original index
                return evaluateTargetWithNextElement(inputTarget, index, true);
            // Check Next Element with next Target Token with updated index
            return evaluateTargetWithNextElement(inputTarget, updatedIndex, true);
        }
        // If there are no more Pattern Elements and If Next Target Token is End
        else if(isFinalTargetChar(inputTarget, updatedIndex)) {
            // Returns Current Index if the next Target Token can the end
             return isPastMaxIndex(inputTarget, updatedIndex) ? updatedIndex : FAIL_INDEX_VALUE;
        }

        // Returns FAIL_INDEX_VALUE if None of the conditions above were met
        return FAIL_INDEX_VALUE;
    }
}

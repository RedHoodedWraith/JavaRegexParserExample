package RegexParts.Group;

import RegexParts.Exceptions.RegexSyntaxError;
import RegexParts.Quantifier.ZeroOrMore;

public class RoundBracketEnd extends RoundBracketTemplate {

    private final RoundBracketStart startOfCurrentGroup;

    public RoundBracketEnd(char[] patt, int index, int groupLayer) throws RegexSyntaxError {
        super(patt, index, --groupLayer,')', true, true,
                '.', '|', '*', '(', ')');

        try {
            startOfCurrentGroup = groupStartList.remove(groupLayer);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("Requested Invalid Index: " + (groupLayer) +
                    "\nGroup Start Element List Size: " + groupStartList.size());
        }
    }

    private boolean isNextTargetRepeatable() {
        return getNextElement() instanceof ZeroOrMore;
    }

    public RoundBracketStart getStartOfCurrentGroup() {
        return startOfCurrentGroup;
    }

    @Override
    public int evaluate(char[] inputTarget, int index) {

        // Repeats if Next in Pattern is Zero or More
        int updatedIndex = isNextTargetRepeatable() ?
                evaluateNextTargetWithElement(this.getStartOfCurrentGroup(), inputTarget, index) : index;

        // Use current index value if post recursion value is FAIL_INDEX_VALUE
        updatedIndex = updatedIndex != FAIL_INDEX_VALUE ? updatedIndex : index;

        // If Next Target Token is End
        if(isNextTargetCharEnd(inputTarget, index)) {
            // Returns true if the next Target Token can the end
            return canNextTargetTokenBeEnd() ? updatedIndex : FAIL_INDEX_VALUE;
        }
        // If Next Pattern Token is Not End (If Next Token is Not Null)
        else if(!isNextElementEnd()) {
            // If Recursive Group Search Succeeded
            if(updatedIndex < 0)
                // Check Next Element with next Target Token with updated index
                return evaluateNextTargetWithNextElement(inputTarget, index);
            // Check Next Element with next Target Token with originalIndex
            return evaluateNextTargetWithNextElement(inputTarget, index);
        }

        // Returns FAIL_INDEX_VALUE if None of the conditions above were met
        return FAIL_INDEX_VALUE;
    }
}

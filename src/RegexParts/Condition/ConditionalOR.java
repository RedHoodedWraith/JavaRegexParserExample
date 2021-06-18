package RegexParts.Condition;

import RegexParts.Exceptions.RegexSyntaxError;
import RegexParts.Group.RoundBracketStart;
import RegexParts.RegexElement;

public class ConditionalOR extends RegexElement {

    public ConditionalOR(char[] patt, int index, int groupLayer, RoundBracketStart groupStart) throws RegexSyntaxError {
        super(patt, index, groupLayer,'|', true, true,
                groupStart,'.', '|', '(');
    }

    @Override
    public boolean evaluate(char[] inputTarget, int index) {
        return false;
    }
}

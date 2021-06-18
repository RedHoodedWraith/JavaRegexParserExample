package RegexParts.Condition;

import RegexParts.Exceptions.RegexSyntaxError;
import RegexParts.Group.RoundBracketStart;
import RegexParts.RegexElement;

import java.util.ArrayList;

public class ConditionalOR extends RegexElement {

    public ConditionalOR(char[] patt, int index, int groupLayer, ArrayList<RoundBracketStart> groupStartList) throws RegexSyntaxError {
        super(patt, index, groupLayer,'|', true, true,
                groupStartList,'.', '|', '(');
    }

    @Override
    public boolean evaluate(char[] inputTarget, int index) {
        return false;
    }
}

package RegexParts.Group;

import RegexParts.Exceptions.RegexSyntaxError;

public class RoundBracketStart extends RoundBracketTemplate {

    public RoundBracketStart(char[] patt, int index, int groupLayer) throws RegexSyntaxError {
        super(patt, index, ++groupLayer,'(', true, false,
                SPEC_CHAR_ANY, SPEC_CHAR_PIPE, SPEC_CHAR_GROUP_OPEN, SPEC_CHAR_GROUP_CLOSE);
    }

    @Override
    public boolean evaluate(char[] inputTarget, int index) {
        return false;
    }
}

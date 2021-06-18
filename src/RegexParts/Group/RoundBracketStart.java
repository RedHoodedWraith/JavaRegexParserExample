package RegexParts.Group;

import RegexParts.Exceptions.RegexSyntaxError;

public class RoundBracketStart extends RoundBracketTemplate {

    public RoundBracketStart(char[] patt, int index, int groupLayer) throws RegexSyntaxError {
        super(patt, index, ++groupLayer,'(', true, false,
                SPEC_CHAR_ANY, SPEC_CHAR_PIPE, SPEC_CHAR_GROUP_OPEN, SPEC_CHAR_GROUP_CLOSE);

        groupStartList.add(this);

        // Asserts that the Element at the given index matches the group layer number
        assert groupStartList.get(groupLayer) == this;
    }

    @Override
    public int evaluate(char[] inputTarget, int index) {
        return getNextElement().evaluate(inputTarget, index);
    }
}

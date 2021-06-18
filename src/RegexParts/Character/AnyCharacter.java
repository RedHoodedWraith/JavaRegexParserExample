package RegexParts.Character;

import RegexParts.Exceptions.RegexSyntaxError;

public class AnyCharacter extends RegexCharacter {

    public AnyCharacter(char[] patt, int index, int groupLayer) throws RegexSyntaxError {
        super(patt, index, groupLayer,'.');
    }

    @Override
    public boolean evaluate(char[] inputTarget, int index) {
        char c = inputTarget[index];

        

        if(isLiteralCharacter(c)) {
            if(isNextElementEnd())
                return index == inputTarget.length-1;

            return this.getNextElement().evaluate(inputTarget, index + 1);
        }

        return false;
    }
}

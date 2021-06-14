package RegexParts;

import RegexParts.Character.AnyCharacter;
import RegexParts.Character.LiteralCharacter;
import RegexParts.Condition.ConditionalOR;
import RegexParts.Exceptions.InvalidNextCharacterPatternException;
import RegexParts.Group.RoundBracketEnd;
import RegexParts.Group.RoundBracketStart;
import RegexParts.Quantifier.ZeroOrMore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public abstract class RegexElement {

    public static final char SPEC_CHAR_ANY = '.',
            SPEC_CHAR_PIPE = '|', SPEC_CHAR_QUANT = '*',
            SPEC_CHAR_GROUP_OPEN = '(', SPEC_CHAR_GROUP_CLOSE = ')';

    public static final Character[] SPECIAL_CHARACTERS = {SPEC_CHAR_ANY, SPEC_CHAR_PIPE, SPEC_CHAR_QUANT,
            SPEC_CHAR_GROUP_OPEN, SPEC_CHAR_GROUP_CLOSE};
    public static final ArrayList<Character> SPECIAL_CHARACTERS_LIST = new ArrayList<>(Arrays.asList(SPECIAL_CHARACTERS));

    private final char tokenChar;
    private final ArrayList<Character> validNextTokens;
    private final boolean nextTokenCanBeLiteral, nextTokenCanBeEnd;

    private final RegexElement nextElement;

    protected RegexElement(char[] patt, int index, char tokenChar, boolean nextTokenCanBeLiteral, boolean nextTokenCanBeEnd, Character... validNextTokensRaw) throws InvalidNextCharacterPatternException {
        this.tokenChar = tokenChar;
        this.nextTokenCanBeLiteral = nextTokenCanBeLiteral;
        this.nextTokenCanBeEnd = nextTokenCanBeEnd;
        this.validNextTokens = new ArrayList<>();
        Collections.addAll(validNextTokens, validNextTokensRaw);

        int nextIndex = index + 1;
        if(checkValidRegexSyntax(patt, nextIndex)) {
            this.nextElement = buildRegexElement(patt, nextIndex);
        } else {
            char[] remainingPatt = Arrays.copyOfRange(patt, nextIndex, patt.length);
            throw new InvalidNextCharacterPatternException(tokenChar, remainingPatt);
        }
    }

    public static RegexElement buildRegexElement(char[] patt, int index) throws InvalidNextCharacterPatternException {
        assert index >= 0 && index <= patt.length;

        // Reached String End
        if(index >= patt.length)
            return null;

        char c = patt[index];

        return switch (c) {
            case SPEC_CHAR_ANY -> new AnyCharacter(patt, index);
            case SPEC_CHAR_PIPE -> new ConditionalOR(patt, index);
            case SPEC_CHAR_QUANT -> new ZeroOrMore(patt, index);
            case SPEC_CHAR_GROUP_OPEN -> new RoundBracketStart(patt, index);
            case SPEC_CHAR_GROUP_CLOSE -> new RoundBracketEnd(patt, index);
            default -> new LiteralCharacter(patt, index, c);
        };
    }

    public static boolean isLiteralCharacter(char c) {
        return LiteralCharacter.isLiteralCharacter(c);
    }

    public boolean isTokenChar(char c) {
        return c == tokenChar;
    }

    protected boolean isNextTokenCanBeEnd() {
        return nextTokenCanBeEnd;
    }

    public boolean isNextTokenValid(Character c) {
        return validNextTokens.contains(c) || (nextTokenCanBeLiteral && isLiteralCharacter(c));
    }

    public RegexElement getNextElement() {
        return nextElement;
    }

    public boolean checkValidRegexSyntax(char[] patt, int index) {
        assert index >= 0 && index < patt.length;

        char c = patt[index];

        if(index == patt.length - 1) {
            return isTokenChar(c) && isNextTokenCanBeEnd();
        }

        char nextC = patt[index + 1];
        return isTokenChar(c) && isNextTokenValid(nextC);
    }
}

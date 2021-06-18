package RegexParts;

import RegexParts.Character.AnyCharacter;
import RegexParts.Character.LiteralCharacter;
import RegexParts.Condition.ConditionalOR;
import RegexParts.Exceptions.RegexIncompleteGroupError;
import RegexParts.Exceptions.RegexOtherError;
import RegexParts.Exceptions.RegexSyntaxError;
import RegexParts.Group.RoundBracketEnd;
import RegexParts.Group.RoundBracketStart;
import RegexParts.Quantifier.ZeroOrMore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

public abstract class RegexElement {

    public static final int FAIL_INDEX_VALUE = -1;

    public static final char SPEC_CHAR_ANY = '.',
            SPEC_CHAR_PIPE = '|', SPEC_CHAR_QUANT = '*',
            SPEC_CHAR_GROUP_OPEN = '(', SPEC_CHAR_GROUP_CLOSE = ')';

    public static final Character[] SPECIAL_CHARACTERS = {SPEC_CHAR_ANY, SPEC_CHAR_PIPE, SPEC_CHAR_QUANT,
            SPEC_CHAR_GROUP_OPEN, SPEC_CHAR_GROUP_CLOSE};
    public static final ArrayList<Character> SPECIAL_CHARACTERS_LIST = new ArrayList<>(Arrays.asList(SPECIAL_CHARACTERS));

    public static final Character[] INVALID_FIRST_CHARACTERS = {SPEC_CHAR_QUANT, SPEC_CHAR_GROUP_CLOSE};
    public static final ArrayList<Character> INVALID_FIRST_CHARACTERS_LIST = new ArrayList<>(Arrays.asList(INVALID_FIRST_CHARACTERS));

    private final char tokenChar;
    private final ArrayList<Character> validNextTokens;
    private final boolean nextTokenCanBeLiteral, nextTokenCanBeEnd;

    private final RegexElement nextElement;

    protected RegexElement(char[] patt, int index, int groupLayer, char tokenChar, boolean nextTokenCanBeLiteral,
                           boolean nextTokenCanBeEnd, Character... validNextTokensRaw)
            throws RegexSyntaxError {
        this.tokenChar = tokenChar;
        this.nextTokenCanBeLiteral = nextTokenCanBeLiteral;
        this.nextTokenCanBeEnd = nextTokenCanBeEnd;
        this.validNextTokens = new ArrayList<>();
        Collections.addAll(validNextTokens, validNextTokensRaw);

        int nextIndex = index + 1;
        if(checkValidRegexSyntax(patt, index)) {
            this.nextElement = buildRegexElement(patt, nextIndex, groupLayer);
        } else {
            char[] remainingPatt = Arrays.copyOfRange(patt, nextIndex, patt.length);
            throw new RegexSyntaxError(tokenChar, index, remainingPatt, patt);
        }
    }

    protected static RegexElement buildRegexElement(char[] patt, int index, int groupLayer) throws RegexSyntaxError {
        assert index >= 0 && index <= patt.length;

        // Reached String End
        if(index >= patt.length) {
            // Throws an exception if the brackets are not correctly opened and closed
            if(groupLayer != 0)
                throw new RegexIncompleteGroupError(patt);
            return null;
        }


        char c = patt[index];

        if(index == 0 && INVALID_FIRST_CHARACTERS_LIST.contains(c)){
            throw new RegexSyntaxError(c, index, patt, patt);
        }

        return switch (c) {
            case SPEC_CHAR_ANY -> new AnyCharacter(patt, index, groupLayer);
            case SPEC_CHAR_PIPE -> new ConditionalOR(patt, index, groupLayer);
            case SPEC_CHAR_QUANT -> new ZeroOrMore(patt, index, groupLayer);
            case SPEC_CHAR_GROUP_OPEN -> new RoundBracketStart(patt, index, groupLayer);
            case SPEC_CHAR_GROUP_CLOSE -> new RoundBracketEnd(patt, index, groupLayer);
            default -> new LiteralCharacter(patt, index, groupLayer, c);
        };
    }

    public static RegexElement buildRegexElement(char[] patt) throws RegexSyntaxError {
        return buildRegexElement(patt, 0, 0);
    }

    public static boolean isPastMaxIndex(char[] target, int index) {
        return index >= target.length;
    }

    public static boolean isTargetCharEnd(char[] target, int currentIndex) {
        return isPastMaxIndex(target, currentIndex);
    }

    public static boolean isFinalTargetChar(char[] target, int currentIndex) {
        return isTargetCharEnd(target, currentIndex + 1);
    }

    public static boolean isLiteralCharacter(char c) {
        return LiteralCharacter.isLiteralCharacter(c);
    }

    public abstract int evaluate(char[] inputTarget, int index);

    public int evaluateTarget(String input) {
        return this.evaluate(input.toCharArray(), 0);
    }

    protected int evaluateNextTargetWithElement(RegexElement element, char[] inputTarget, int currentIndex) {
        return element.evaluate(inputTarget, ++currentIndex);
    }

    protected int evaluateNextTargetWithNextElement(char[] inputTarget, int currentIndex) {
        if(isNextElementNull())
            return currentIndex;
        return this.evaluateNextTargetWithElement(this.getNextElement(), inputTarget, currentIndex);
    }

    protected int evaluateTargetWithNextElement(char[] inputTarget, int currentIndex) {
        return getNextElement().evaluate(inputTarget, currentIndex);
    }

    public boolean isTokenChar(char c) {
        return c == tokenChar;
    }

    public boolean isNextElementNull() {
        return getNextElement() == null;
    }

    public boolean canTargetTokenBeEnd() {
        return isNextElementNull();
    }

    protected boolean canElementBeEnd() {
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
            return isTokenChar(c) && canElementBeEnd();
        }

        char nextC = patt[index + 1];
        return isTokenChar(c) && isNextTokenValid(nextC);
    }

    public static void pairStartEndGroupElements(RegexElement firstElement, char[] pattern) throws RegexOtherError {
        RegexElement elm = firstElement;
        int layer = 0;
        Stack<RoundBracketStart> startBracketCollect = new Stack<>();

        while(elm != null) {
            if(elm instanceof RoundBracketStart) {
                startBracketCollect.push((RoundBracketStart) elm);
                layer++;
            } else if(elm instanceof RoundBracketEnd) {
                ((RoundBracketEnd) elm).setStartOfCurrentGroup(startBracketCollect.pop());
                layer--;
            }
            if(layer != startBracketCollect.size())
                throw new RegexOtherError("Bracket Pairing Failed",
                        "Layer Count and Stack Size do not match.\n\t" +
                        "Layer: " + layer + "\n\tLayer Size: " + startBracketCollect.size());
            elm = elm.getNextElement();
        }

        if(layer != 0)
            throw new RegexIncompleteGroupError(pattern);
    }
}

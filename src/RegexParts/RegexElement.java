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
    private final boolean nextTokenCanBeLiteral, canBeLastElement;
    private boolean firstElementState;

    private final RegexElement nextElement;

    protected RegexElement(char[] patt, int index, int groupLayer, char tokenChar, boolean firstElementState,
                           boolean nextTokenCanBeLiteral, boolean nextTokenCanBeEnd, Character... validNextTokensRaw)
            throws RegexSyntaxError {
        this.tokenChar = tokenChar;
        this.firstElementState = firstElementState;
        this.nextTokenCanBeLiteral = nextTokenCanBeLiteral;
        this.canBeLastElement = nextTokenCanBeEnd;
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

    protected RegexElement(char[] patt, int index, int groupLayer, char tokenChar,
                           boolean nextTokenCanBeLiteral, boolean nextTokenCanBeEnd, Character... validNextTokensRaw)
            throws RegexSyntaxError {
        this(patt, index, groupLayer, tokenChar, false, nextTokenCanBeLiteral, nextTokenCanBeEnd, validNextTokensRaw);
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

        switch (c) {
            case SPEC_CHAR_ANY:
                return new AnyCharacter(patt, index, groupLayer);
            case SPEC_CHAR_PIPE:
                return new ConditionalOR(patt, index, groupLayer);
            case SPEC_CHAR_QUANT:
                return new ZeroOrMore(patt, index, groupLayer);
            case SPEC_CHAR_GROUP_OPEN:
                return new RoundBracketStart(patt, index, groupLayer);
            case SPEC_CHAR_GROUP_CLOSE:
                return new RoundBracketEnd(patt, index, groupLayer);
            default:
                return new LiteralCharacter(patt, index, groupLayer, c);
        }
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

    public abstract int evaluate(char[] inputTarget, int index, boolean resultFromPreviousElement);

    public int skipToMiniEnd(char[] inputTarget, int index, boolean resultFromPreviousElement) {
        RegexElement elm = this;
        while(!(elm == null || elm instanceof ConditionalOR)) {
            elm = elm.getNextElement();
        }
        return evaluateTargetWithElement(elm, inputTarget, index, resultFromPreviousElement);
    }

    public int evaluateTarget(String input) {
        return this.evaluate(input.toCharArray(), 0, false);
    }

    protected int evaluateTargetWithElement(RegexElement element, char[] inputTarget, int currentIndex, boolean resultFromPreviousElement) {
        if(element == null) {
            return FAIL_INDEX_VALUE;
        }
        return element.evaluate(inputTarget, currentIndex, resultFromPreviousElement);
    }

    protected int evaluateNextTargetWithElement(RegexElement element, char[] inputTarget, int currentIndex, boolean resultFromPreviousElement) {
        return evaluateTargetWithElement(element, inputTarget, ++currentIndex, resultFromPreviousElement);
    }

    protected int evaluateTargetWithNextElement(char[] inputTarget, int currentIndex, boolean resultFromPreviousElement) {
        return evaluateTargetWithElement(this.getNextElement(), inputTarget, currentIndex, resultFromPreviousElement);
    }

    protected int evaluateNextTargetWithNextElement(char[] inputTarget, int currentIndex, boolean resultFromPreviousElement) {
        return evaluateTargetWithElement(this.getNextElement(), inputTarget, ++currentIndex, resultFromPreviousElement);
    }

    public boolean isTokenChar(char c) {
        return c == tokenChar;
    }

    public boolean isNextElementNull() {
        return getNextElement() == null;
    }

    protected boolean canElementBeEnd() {
        return canBeLastElement;
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
        elm.setAsFirstElement();
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

    public boolean isFirstElement() {
        return firstElementState;
    }

    public void setAsFirstElement() {
        this.firstElementState = true;
    }
}

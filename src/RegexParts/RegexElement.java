package RegexParts;

import RegexParts.Character.LiteralCharacter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public abstract class RegexElement {

    public static final Character[] SPECIAL_CHARACTERS = {'.', '|', '*', '(', ')'};
    public static final ArrayList<Character> SPECIAL_CHARACTERS_LIST = new ArrayList<>(Arrays.asList(SPECIAL_CHARACTERS));

    private final char tokenChar;
    private final ArrayList<Character> validNextTokens;
    private final boolean nextTokenCanBeLiteral, nextTokenCanBeEnd;

    protected RegexElement(char tokenChar, boolean nextTokenCanBeLiteral, boolean nextTokenCanBeEnd, Character... validNextTokensRaw){
        this.tokenChar = tokenChar;
        this.nextTokenCanBeLiteral = nextTokenCanBeLiteral;
        this.nextTokenCanBeEnd = nextTokenCanBeEnd;
        this.validNextTokens = new ArrayList<>();
        Collections.addAll(validNextTokens, validNextTokensRaw);
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public abstract class RegexElement {

    public static final Character[] SPECIAL_CHARACTERS = {'.', '|', '*', '(', ')'};
    public static final ArrayList<Character> SPECIAL_CHARACTERS_LIST = new ArrayList<Character>(Arrays.asList(SPECIAL_CHARACTERS));

    private final char tokenChar;
    private final ArrayList<Character> validNextTokens;
    private final boolean nextTokenCanBeLiteral;

    protected RegexElement(char tokenChar, boolean nextTokenCanBeLiteral, Character... validNextTokensRaw){
        this.tokenChar = tokenChar;
        this.nextTokenCanBeLiteral = nextTokenCanBeLiteral;
        this.validNextTokens = new ArrayList<>();
        Collections.addAll(validNextTokens, validNextTokensRaw);
    }

    public static boolean isLiteralCharacter(char c) {
        return LiteralCharacter.isLiteralCharacter(c);
    }

    public boolean isTokenChar(char c) {
        return c == tokenChar;
    }

    public boolean isNextTokenValid(Character c) {
        return validNextTokens.contains(c) || (nextTokenCanBeLiteral && isLiteralCharacter(c));
    }

    public abstract boolean checkValidRegexSyntax(char[] patt, int index);

}

public abstract class RegexCharacter extends RegexElement {

    protected RegexCharacter(char tokenChar) {
        super(tokenChar, true, SPECIAL_CHARACTERS);
    }


}

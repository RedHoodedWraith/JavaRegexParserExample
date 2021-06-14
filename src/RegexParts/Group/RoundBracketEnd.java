package RegexParts.Group;

public class RoundBracketEnd extends RoundBracketTemplate {

    protected RoundBracketEnd() {
        super(')', true, true, '.', '|', '*', '(', ')');
    }

}

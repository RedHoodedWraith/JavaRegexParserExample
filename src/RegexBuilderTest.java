import RegexParts.Exceptions.RegexSyntaxError;
import RegexParts.RegexElement;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class RegexBuilderTest {

    private final String DEFAULT_EXPECTED_LIST = "expected.txt";
    private final String RESULT_NO = "NO", RESULT_YES = "YES", RESULT_SE = "SYNTAX ERROR";

    private ArrayList<String> validRegexes = new ArrayList<>();
    private ArrayList<String> invalidRegexes = new ArrayList<>();
    private ArrayList<String> unknownRegexes = new ArrayList<>();

    protected void prepareTests() throws FileNotFoundException {
        ArrayList<String> patternsAsStrings = RegexBuilder.importPatterns(RegexBuilder.DEFAULT_PATTERN_FILE);
        ArrayList<String> lines = RegexBuilder.importPatterns(DEFAULT_EXPECTED_LIST);

        // Checks the expressions list and expected list have the same amount of elements
        int expectedSize = lines.size();
        assertEquals(expectedSize, patternsAsStrings.size());

        for(int i=0; i<expectedSize; i++) {
            String expectedLine = lines.get(i),
            expressionLine = patternsAsStrings.get(i);
            assert expectedLine.contains(expressionLine);

            String[] expectedPart = expectedLine.split(":");
            assert expectedPart.length >= 2;
            String expectedCondition = expectedPart[0];

            switch (expectedCondition) {
                case RESULT_SE -> invalidRegexes.add(expressionLine);
                case RESULT_YES, RESULT_NO -> validRegexes.add(expressionLine);
                default -> unknownRegexes.add(expressionLine);
            }
        }
    }

    @Test
    public void validSyntaxTest() throws RegexSyntaxError, FileNotFoundException {
        prepareTests();
        for(String p : validRegexes){
            RegexBuilder.buildRegex(p);
        }
    }

    @Test
    public void invalidSyntaxTest() throws FileNotFoundException {
        prepareTests();
        for(String p : invalidRegexes){
            try {
                RegexBuilder.buildRegex(p);
                fail("The following pattern is expected to be invalid: " + p);
            } catch (RegexSyntaxError regexSyntaxError) {

            }
        }
    }

}
import RegexParts.Exceptions.RegexSyntaxError;
import RegexParts.RegexElement;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class RegexBuilderTest {

    private final String
            DEFAULT_EXPECTED_LIST = "expected.txt",
            DEFAULT_TARGETS_LIST = "targets.txt";

    private final String
            RESULT_NO = "NO",
            RESULT_YES = "YES",
            RESULT_SE = "SYNTAX ERROR";

    private final ArrayList<String> allRegexes = new ArrayList<>();
    private final ArrayList<String> allTestTargets = new ArrayList<>();
    private final ArrayList<String> validRegexes = new ArrayList<>();
    private final ArrayList<String> invalidRegexes = new ArrayList<>();
    private final ArrayList<String> unknownRegexes = new ArrayList<>();
    private final ArrayList<Boolean> evaluationList = new ArrayList<>();

    protected void prepareTests() throws FileNotFoundException {
        ArrayList<String> patternsAsStrings = RegexBuilder.importPatterns(RegexBuilder.DEFAULT_PATTERN_FILE);
        ArrayList<String> testDataLines = RegexBuilder.importPatterns(DEFAULT_TARGETS_LIST);
        allTestTargets.addAll(testDataLines);
        ArrayList<String> expectedResultsLines = RegexBuilder.importPatterns(DEFAULT_EXPECTED_LIST);

        // Checks the expressions list, targets list and expected list have the same amount of elements
        int expectedSize = expectedResultsLines.size();
        assertEquals(expectedSize, testDataLines.size());
        assertEquals(expectedSize, patternsAsStrings.size());

        for(int i=0; i<expectedSize; i++) {
            int lineId = i+1;
            String expectedLine = expectedResultsLines.get(i),
            expressionLine = patternsAsStrings.get(i),
            targetLine = testDataLines.get(i);


            String[] expectedParts = expectedLine.split(":[\\s]*|\\swith\\s");
            int numExpectPartsSplit = expectedParts.length;
            //System.out.println("[" + lineId + "]: " + Arrays.toString(expectedParts));
            String expectedCondition = expectedParts[0],
            expectedRegex = expectedParts[1], expectedTarget;

            if(numExpectPartsSplit == 3) {
                expectedTarget = expectedParts[2];
                // Checks that the Expected Results list contains test target
                assertEquals(expectedTarget, targetLine);
            } else {
                assertEquals(2, numExpectPartsSplit);
            }

            // Checks that the Expected Results list contains the correct regex and test target
            assertEquals(expressionLine, expectedRegex);

            switch (expectedCondition) {
                case RESULT_SE -> {
                    invalidRegexes.add(expressionLine);
                    allRegexes.add(expressionLine);
                    evaluationList.add(false);
                }
                case RESULT_YES -> {
                    validRegexes.add(expressionLine);
                    allRegexes.add(expressionLine);
                    evaluationList.add(true);
                }
                case RESULT_NO -> {
                    validRegexes.add(expressionLine);
                    allRegexes.add(expressionLine);
                    evaluationList.add(false);
                }
                default -> {
                    unknownRegexes.add(expressionLine);
                    allRegexes.add(expressionLine);
                    evaluationList.add(false);
                }
            }
        }

        assertEquals(expectedSize, allRegexes.size());
        assertEquals(expectedSize, evaluationList.size());
        assertEquals(expectedSize, allTestTargets.size());
        assertEquals(allRegexes.size(), evaluationList.size());
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

    @Test
    public void testPassFailList() throws FileNotFoundException {
        prepareTests();
        for(int i=0; i<allRegexes.size(); i++){
            int id = i + 1;
            RegexElement r;
            String p = allRegexes.get(i), t = allTestTargets.get(i);
            boolean s = evaluationList.get(i), result;

            try {
                r = RegexBuilder.buildRegex(p);
                if(s != (result = r.evaluate(t))){
                    fail("\n\tID: " + id + "\n\tRegex: " + p + "\n\tTarget: " + t +
                            "\n\tResult: " + result + "\n\tExpected: " + s + "\n");
                }
            } catch (RegexSyntaxError regexSyntaxError) {
                if(s)
                    fail("Syntax Error on Regex expected to Pass: " + p);
            } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
                fail("Array Related Error:\n\tID: " + id + "\n\tRegex: " + p + "\n\tTarget: " + t +
                        "\n\tExpected: " + s + "\n");
            }
        }
    }

}
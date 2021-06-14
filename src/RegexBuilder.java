import RegexParts.Exceptions.RegexSyntaxError;
import RegexParts.RegexElement;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class RegexBuilder {

    public static RegexElement buildRegex(String regexPatternStr) throws RegexSyntaxError {
        char[] regexPattern = regexPatternStr.toCharArray();
        return RegexElement.buildRegexElement(regexPattern, 0);
    }

    public static ArrayList<RegexElement> buildRegexPatternsFromFile(String filePathLoc) throws FileNotFoundException, RegexSyntaxError {
        ArrayList<RegexElement> regexElements = new ArrayList<>();
        File filePatt = new File(filePathLoc);
        Scanner sc = new Scanner(filePatt);
        while(sc.hasNextLine()) {
            String patt = sc.nextLine();
            regexElements.add(buildRegex(patt));
        }
        return regexElements;
    }

    public static void main(String... args) {

    }

}

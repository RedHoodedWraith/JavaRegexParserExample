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

    public static ArrayList<String> importPatterns(String filePathLoc) throws FileNotFoundException {
        ArrayList<String> lines = new ArrayList<>();
        File filePatt = new File(filePathLoc);
        Scanner sc = new Scanner(filePatt);

        // Saves the lines into an arrayList
        while(sc.hasNextLine()) {
            lines.add(sc.nextLine());
        }

        return lines;
    }

    public static ArrayList<RegexElement> buildRegexPatternsFromFile(ArrayList<String> lines) {
        ArrayList<RegexElement> regexElements = new ArrayList<>();

        // Index in text file starts at 1
        int i = 1;
        for(String patt : lines) {
            System.out.print("Reading Line [" + i + "]: " + patt + "\t");
            try {
                regexElements.add(buildRegex(patt));
                System.out.println("");
            } catch (RegexSyntaxError regexSyntaxError) {
                System.out.println("[SYNTAX ERROR]");
                regexSyntaxError.printStackTrace();
            }
            i++;
        }

        return regexElements;
    }

    public static void main(String... args) {
        try {
            ArrayList<RegexElement> elems = buildRegexPatternsFromFile(importPatterns("expressions.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}

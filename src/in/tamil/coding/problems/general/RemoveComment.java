package in.tamil.coding.problems.general;

public class RemoveComment {

    public static String removeComments(String code) {
        String[] linesOfCode = getCodeStringSplitIntoLinesOfCode(code);
        StringBuilder correctedCodeStrBuilder = new StringBuilder();
        populatedCorrectedCode(linesOfCode, correctedCodeStrBuilder);
        return correctedCodeStrBuilder.toString();
    }

    private static void populatedCorrectedCode(String[] linesOfCode, StringBuilder correctedCodeStrBuilder) {
        StringBuilder correctedLineStrBuilder = new StringBuilder();
        boolean isCurrCharPartOfMultiLineComment = false;
        for (String currLine : linesOfCode) {
            if (isCompleteLineCommentedAndDontHaveMultiLineComment(currLine)) {
                continue;
            }
            if (!isCurrCharPartOfMultiLineComment) {
                correctedLineStrBuilder = new StringBuilder();
            }
            isCurrCharPartOfMultiLineComment = getCorrectedLineIfNotPartOfMultiLineComment(correctedLineStrBuilder, isCurrCharPartOfMultiLineComment, currLine);
            if (!isCurrCharPartOfMultiLineComment) {
                correctedCodeStrBuilder.append(correctedLineStrBuilder).append("\n");
            }
        }
    }

    private static boolean getCorrectedLineIfNotPartOfMultiLineComment(StringBuilder correctedLineStrBuilder, boolean isCurrCharPartOfMultiLineComment, String currLine) {
        int currLineLength = currLine.length();
        for (int i = 0; i < currLine.length(); i++) {
            char currChar = currLine.charAt(i);
            if (!isCurrCharPartOfMultiLineComment) {
                if (currChar == '/') {
                    if (checkForTheNextChar(currLine, currLineLength, i, '/')) {
                        break;
                    } else if (checkForTheNextChar(currLine, currLineLength, i, '*')) {
                        i++;
                        isCurrCharPartOfMultiLineComment = true;
                    } else {
                        correctedLineStrBuilder.append(currChar);
                    }
                } else {
                    correctedLineStrBuilder.append(currChar);
                }
            } else if (currChar == '*' && checkForTheNextChar(currLine, currLineLength, i, '/')) {
                isCurrCharPartOfMultiLineComment = false;
                i++;
            }
        }
        return isCurrCharPartOfMultiLineComment;
    }

    private static boolean checkForTheNextChar(String currLine, int currLineLength, int i, char nextCharToValidate) {
        return i < currLineLength && currLine.charAt(i + 1) == nextCharToValidate;
    }

    private static String[] getCodeStringSplitIntoLinesOfCode(String code) {
        return code.split("\\r?\\n");
    }

    private static boolean isCompleteLineCommentedAndDontHaveMultiLineComment(String currentLine) {
        return currentLine.stripLeading().startsWith("//") && !currentLine.contains("*/");
    }

    public static void main(String[] args) {
        System.out.print(RemoveComment.removeComments("package com.tamil.coding.problems;\n" +
                "\n" +
                "public class Main {\n" +
                "\n" +
                "    public static void main(String[] args) {\n" +
                "        System.out.println(\"Hello!!!\");\n" +
                "    }\n" +
                "\n" +
                "    private void dummyMethodForComments() {\n" +
                "        // hi hello\n" +
                "        System.out.println(\"Hello!!!\"); // seth\n" +
                "        /*\n" +
                "        hi hello multi\n" +
                "        // nested single line\n" +
                "        System.out.println(\"Hello inside nested 001 !!!\");\n" +
                "        line ending with just one slash /\n" +
                "         */\n" +
                "        // /*\n" +
                "        System.out.println(\"Valid stmnt\");\n" +
                "        // */\n" +
                "\n" +
                "    }\n" +
                "}\n"));
    }

}

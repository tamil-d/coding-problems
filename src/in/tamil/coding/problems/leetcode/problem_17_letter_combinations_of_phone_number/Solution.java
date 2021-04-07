package in.tamil.coding.problems.leetcode.problem_17_letter_combinations_of_phone_number;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Solution {
    private static final Map<Character, String> phoneDigitsToLetterStringMap = Map.of(
            '2', "abc", '3', "def", '4', "ghi", '5', "jkl",
            '6', "mno", '7', "pqrs", '8', "tuv", '9', "wxyz");

    public List<String> letterCombinations(String givenDigitsRange) {
        List<String> possibleCombinations = new ArrayList<>();
        // If the given input is empty, immediately return an empty array
        if (givenDigitsRange == null || givenDigitsRange.isEmpty()) {
            return possibleCombinations;
        }
        // Initiate the recursion with an empty lettersCombination and starting index of 0
        recursivelyBuildPossibleCombinations(0, new StringBuilder(), possibleCombinations, givenDigitsRange);
        return possibleCombinations;
    }

    private void recursivelyBuildPossibleCombinations(int currPhoneDigit, StringBuilder formedLettersCombination, List<String> possibleCombinations, String givenDigitsRange) {
        // Exit Condition for the Recursion:
        //      If the formedLettersCombination is the same length as givenDigitsRange,
        //      then it means, we have atleast one character from each digit included in our formedLettersCombination.
        if (formedLettersCombination.length() == givenDigitsRange.length()) {
            possibleCombinations.add(formedLettersCombination.toString());
            return;
        }

        // Actual logic of getting each character of letters associated to the digit
        // and appending to the current formedString and checking if its eligible to add to the List.
        String possibleLettersForCurrentPhoneDigit = phoneDigitsToLetterStringMap.get(givenDigitsRange.charAt(currPhoneDigit));
        for (char letter : possibleLettersForCurrentPhoneDigit.toCharArray()) {
            recursivelyBuildPossibleCombinations(currPhoneDigit + 1, new StringBuilder(formedLettersCombination).append(letter), possibleCombinations, givenDigitsRange);
        }
    }
}
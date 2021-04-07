package in.tamil.coding.problems.leetcode.problem_3_longest_substring_without_repeating_chars;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int lengthOfLongestSubstring(String givenString) {
        if (givenString == null || "".equals(givenString)) {
            return 0;
        }
        int longestLength = 1;
        int strLen = givenString.length();
        Map<Character, Integer> characterIndexMap = new HashMap<>();
        int startIndexOfSubstring = -1;
        for (int currCharIndex = 0; currCharIndex < strLen; currCharIndex++) {
            char currChar = givenString.charAt(currCharIndex);
            if (characterIndexMap.getOrDefault(currChar, -1) > startIndexOfSubstring) {
                startIndexOfSubstring = characterIndexMap.get(currChar);
            }
            if ((currCharIndex - startIndexOfSubstring) > longestLength) {
                longestLength = currCharIndex - startIndexOfSubstring;
            }
            characterIndexMap.put(currChar, currCharIndex);
        }
        return longestLength;
    }
}

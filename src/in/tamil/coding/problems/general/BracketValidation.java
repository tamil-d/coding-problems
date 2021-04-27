package in.tamil.coding.problems.general;

import java.util.*;
// Add any extra import statements you may need here


public class BracketValidation {
    // Add any helper functions you may need here


    boolean isBalanced(String s) {
        // Write your code here
        if(s == null || s.trim().isEmpty()) {
            return true;
        }
        if(s.length() %2 != 0) {
            return false;
        }
        Stack<Character> bracketStk = new Stack<>();
        Set<Character> closedBrackets = new HashSet<>();
        closedBrackets.add(')');
        closedBrackets.add('}');
        closedBrackets.add(']');
        for(Character currBracket : s.toCharArray()) {
            if(bracketStk.isEmpty()) {
                if(closedBrackets.contains(currBracket)) {
                    return false;
                } else {
                    bracketStk.push(currBracket);
                }
            } else if (currBracket == ')' && bracketStk.peek() == '('){
                bracketStk.pop();
            } else if (currBracket == '}' && bracketStk.peek() == '{') {
                bracketStk.pop();
            } else if (currBracket == ']' && bracketStk.peek() == '[') {
                bracketStk.pop();
            } else if(!closedBrackets.contains(currBracket)) {
                bracketStk.push(currBracket);
            } else {
                return false;
            }
        }
        if(!bracketStk.isEmpty()) {
            return false;
        }
        return true;
    }












    // These are the tests we use to determine if the solution is correct.
    // You can add your own at the bottom, but they are otherwise not editable!
    int test_case_number = 1;
    void check(boolean expected, boolean output) {
        boolean result = (expected == output);
        char rightTick = '\u2713';
        char wrongTick = '\u2717';
        if (result) {
            System.out.println(rightTick + " Test #" + test_case_number);
        }
        else {
            System.out.print(wrongTick + " Test #" + test_case_number + ": Expected ");
            System.out.print(expected);
            System.out.print(" Your output: ");
            System.out.print(output);
            System.out.println();
        }
        test_case_number++;
    }
    void printString(String str) {
        System.out.print("[" + str + "]");
    }

    public void run() {
        String s_1 = "{[(])}";
        boolean expected_1 = false;
        boolean output_1 = isBalanced(s_1);
        check(expected_1, output_1);

        String s_2 = "{{[[(())]]}}";
        boolean expected_2 = true;
        boolean output_2 = isBalanced(s_2);
        check(expected_2, output_2);

        // Add your own test cases here

    }

    public static void main(String[] args) {
        new BracketValidation().run();
    }
}
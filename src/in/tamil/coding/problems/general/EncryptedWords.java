package in.tamil.coding.problems.general;

import java.io.*;
import java.util.*;
// Add any extra import statements you may need here


class EncryptedWords {

    // Add any helper functions you may need here
    StringBuilder encrypt(String s) {
        StringBuilder result = new StringBuilder();

        if(s == null || s.trim().isEmpty()) {
            return result;
        }
        int mid = s.length() /2;
        if(mid > 0 && s.length() %2 == 0) {
            mid --;
        }
        result.append(s.charAt(mid));


        if(mid ==1) {
            result.append(s.charAt(0));
            result.append(encrypt(s.substring(mid+1)));
        } else {
            if(mid > 1) {
                result.append(encrypt(s.substring(0,mid-1)));
            }
            if ((mid+1) < s.length()) {
                result.append(encrypt(s.substring(mid+1)));
            }
        }
        return result;
    }

    String findEncryptedWord(String s) {
        // Write your code here
        return encrypt(s).toString();
    }











    // These are the tests we use to determine if the solution is correct.
    // You can add your own at the bottom, but they are otherwise not editable!
    int test_case_number = 1;
    void check(String expected, String output) {
        boolean result = (expected.equals(output));
        char rightTick = '\u2713';
        char wrongTick = '\u2717';
        if (result) {
            System.out.println(rightTick + " Test #" + test_case_number);
        }
        else {
            System.out.print(wrongTick + " Test #" + test_case_number + ": Expected ");
            printString(expected);
            System.out.print(" Your output: ");
            printString(output);
            System.out.println();
        }
        test_case_number++;
    }
    void printString(String str) {
        System.out.print("[\"" + str + "\"]");
    }

    public void run() {
        String s_1 = "abc";
        String expected_1 = "bac";
        String output_1 = findEncryptedWord(s_1);
        check(expected_1, output_1);

        String s_2 = "abcd";
        String expected_2 = "bacd";
        String output_2 = findEncryptedWord(s_2);
        check(expected_2, output_2);

        // Add your own test cases here

    }

    public static void main(String[] args) {
        new EncryptedWords().run();
    }
}

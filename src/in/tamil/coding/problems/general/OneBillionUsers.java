package in.tamil.coding.problems.general;

// Add any extra import statements you may need here


public class OneBillionUsers {

    // Add any helper functions you may need here
    float myPow(float x, int n) {
        long N = n;
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        float ans = 1;
        float current_product = x;
        for (long i = N; i > 0; i /= 2) {
            if ((i % 2) == 1) {
                ans = ans * current_product;
            }
            current_product = current_product * current_product;
        }
        return ans;
    }

    int getBillionUsersDay(float[] growthRates) {
        // Write your code here
        float billion = 1000000000;
        float totalSum = 0;
        int i =0;
        while (totalSum < billion) {
            i++;
            totalSum=0;
            for(int j=0;j<growthRates.length && totalSum < billion;j++) {
                totalSum = totalSum + myPow(growthRates[j],i);
            }
        }
        return i;
    }












    // These are the tests we use to determine if the solution is correct.
    // You can add your own at the bottom, but they are otherwise not editable!
    int test_case_number = 1;

    void check(int expected, int output) {
        boolean result = (expected == output);
        char rightTick = '\u2713';
        char wrongTick = '\u2717';
        if (result) {
            System.out.println(rightTick + " Test #" + test_case_number);
        }
        else {
            System.out.print(wrongTick + " Test #" + test_case_number + ": Expected ");
            printInteger(expected);
            System.out.print(" Your output: ");
            printInteger(output);
            System.out.println();
        }
        test_case_number++;
    }

    void printInteger(int n) {
        System.out.print("[" + n + "]");
    }

    public void run() {
        float[] test_1 = {1.1f, 1.2f, 1.3f};
        int expected_1 = 79;
        int output_1 = getBillionUsersDay(test_1);
        check(expected_1, output_1);

        float[] test_2 = {1.01f, 1.02f};
        int expected_2 = 1047;
        int output_2 = getBillionUsersDay(test_2);
        check(expected_2, output_2);


        // Add your own test cases here

    }
    public static void main(String[] args) {
        new OneBillionUsers().run();
    }
}

/*
Reverse digits of an integer.

Example1: x = 123, return 321
Example2: x = -123, return -321

click to show spoilers.

Have you thought about this?
Here are some good questions to ask before coding. Bonus points for you if you have already thought through this!
If the integer's last digit is 0, what should the output be? ie, cases such as 10, 100.
Did you notice that the reversed integer might overflow? Assume the input is a 32-bit integer, then the reverse of 1000000003 overflows. 
How should you handle such cases?
Throw an exception? Good, but what if throwing an exception is not an option? 
You would then have to re-design the function (ie, add an extra parameter).
*/

public class Solution {
    public int reverse(int x) {
        // check sign
        int sign = 1;
        if (x < 0) {
            sign = -1;
            x = 0 - x;
        }
        // use long to avoid overflow
        long res = 0;
        boolean isOverflow = false;
        while (x > 0) {
            res = res * 10 + x % 10;
            x /= 10;
        }
        if (res > Integer.MAX_VALUE) {
            isOverflow = true;  // throw exception
        }
        res *= sign;
        return (int)res;
    }
}

// Actually no need to check sign
public class Solution {
    public int reverse(int x) {
        // need a flag to notice the user if overflow
        int res = 0;
        while (x != 0) {
            res = res * 10 + x % 10;
            x /= 10;
        }
        return res;
    }
}
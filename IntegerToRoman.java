/*
Given an integer, convert it to a roman numeral.

Input is guaranteed to be within the range from 1 to 3999.
*/

// Solution 1
public class Solution {
    public String intToRoman(int num) {
        char[] arr = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};
        StringBuffer sb = new StringBuffer();
        int curr = 0;   // index in arr
        while (num > 0) {
            int digit = num % 10;
            if (digit <= 3) {    // 0 - 3
                while (digit-- > 0) {
                    sb.insert(0, arr[curr]);
                }
            } else if (digit == 4) {    // 4
                sb.insert(0, arr[curr + 1]);    // need to insert reversely, same as below
                sb.insert(0, arr[curr]);
            } else if (digit <= 8) {     // 5 - 8
                while (digit-- > 5) { 
                    sb.insert(0, arr[curr]);
                }
                sb.insert(0, arr[curr + 1]);
            } else {    // 9
                sb.insert(0, arr[curr + 2]);
                sb.insert(0, arr[curr]);
            }
            num /= 10;
            curr += 2;
        }
        return sb.toString();
    }
}


// Solution 2: Greedy
public class Solution {
    public String intToRoman(int num) {
        String[] arr = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] div =  {1000, 900,  500, 400, 100,  90,   50,  40,   10,   9,   5,    4,   1};
        StringBuffer sb = new StringBuffer();
        for (int i = 0; num > 0; i++) {
            while (num >= div[i]) {
                sb.append(arr[i]);
                num -= div[i];
            }
        }
        return sb.toString();
    }
}
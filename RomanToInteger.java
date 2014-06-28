/*
Given a roman numeral, convert it to an integer.

Input is guaranteed to be within the range from 1 to 3999.
*/

// Solution 1
// Take care of the cases of 4 and 9
public class Solution {
    public int romanToInt(String s) {
        HashMap<Character, Integer> hm = new HashMap<Character, Integer>();
        hm.put('I', 1);
        hm.put('V', 5);
        hm.put('X', 10);
        hm.put('L', 50);
        hm.put('C', 100);
        hm.put('D', 500);
        hm.put('M', 1000);
        int sum = 0;
        int i = 0;
        while (i < s.length()) {
            if (i < s.length() - 1) {
                if (s.charAt(i) == 'I') {
                    if (s.charAt(i + 1) == 'V') {
                        sum += 4;
                        i++;
                    } else if (s.charAt(i + 1) == 'X') {
                        sum += 9;
                        i++;
                    } else {
                        sum += 1;
                    }
                } else if (s.charAt(i) == 'X') {
                    if (s.charAt(i + 1) == 'L') {
                        sum += 40;
                        i++;
                    } else if (s.charAt(i + 1) == 'C') {
                        sum += 90;
                        i++;
                    } else {
                        sum += 10;
                    }
                } else if (s.charAt(i) == 'C') {
                    if (s.charAt(i + 1) == 'D') {
                        sum += 400;
                        i++;
                    } else if (s.charAt(i + 1) == 'M') {
                        sum += 900;
                        i++;
                    } else {
                        sum += 100;
                    }
                } else {
                    sum += hm.get(s.charAt(i));
                }
                i++;
            } else {
                sum += hm.get(s.charAt(i));
                i++;
            }
        }
        return sum;
    }
}


// Solution 2
// Optimized. 4 and 9 are prev < curr
public class Solution {
    public int romanToInt(String s) {
        HashMap<Character, Integer> hm = new HashMap<Character, Integer>();
        hm.put('I', 1);
        hm.put('V', 5);
        hm.put('X', 10);
        hm.put('L', 50);
        hm.put('C', 100);
        hm.put('D', 500);
        hm.put('M', 1000);
        int sum = 0;
        for (int curr = 0; curr < s.length(); curr++) {
            sum += hm.get(s.charAt(curr));
            if (curr > 0 && hm.get(s.charAt(curr - 1)) < hm.get(s.charAt(curr))) {
                sum -= 2 * hm.get(s.charAt(curr - 1));
            }
        }
        return sum;
    }
}
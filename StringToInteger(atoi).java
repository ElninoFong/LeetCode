/*
Implement atoi to convert a string to an integer.
Hint: Carefully consider all possible input cases. 
      If you want a challenge, please do not see below and ask yourself what are the possible input cases.
Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). 
       You are responsible to gather all the input requirements up front.

Requirements for atoi:
1.  The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. 
    Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, 
    and interprets them as a numerical value.
2.  The string can contain additional characters after those that form the integral number, 
    which are ignored and have no effect on the behavior of this function.
3.  If the first sequence of non-whitespace characters in str is not a valid integral number, 
    or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.
4.  If no valid conversion could be performed, a zero value is returned. 
    If the correct value is out of the range of representable values, INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.
*/
    
public class Solution {
    public int atoi(String str) {
        // Better draw a state machine diagram to clarify        
        if (str == null) return 0;
        str = str.trim();
        boolean sign = true;
        if (str.length() == 0) return 0;
        if (str.charAt(0) == '+') {
            str = str.substring(1);
        } else if (str.charAt(0) == '-') {
            sign = false;
            str = str.substring(1);
        }
        long res = 0;   // difference between long and double?
        while (str.length() > 0 && str.charAt(0) >= '0' && str.charAt(0) <= '9') {
            res = res * 10 + (str.charAt(0) - '0');
            str = str.substring(1);
        }
        if (!sign) {
            res *= -1;
        }
        res = Math.min(res, Integer.MAX_VALUE);
        res = Math.max(res, Integer.MIN_VALUE);
        return (int)res;
    }
}
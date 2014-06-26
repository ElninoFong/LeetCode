/*
Determine whether an integer is a palindrome. Do this without extra space.

click to show spoilers.
Some hints:
Could negative integers be palindromes? (ie, -1)
If you are thinking of converting the integer to string, note the restriction of using extra space.
You could also try reversing an integer. However, if you have solved the problem "Reverse Integer", 
you know that the reversed integer might overflow. How would you handle such case?
There is a more generic way of solving this problem.
*/

public class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        int digit = 1;  // how many digits in x
        while (x / digit > 9) {
            digit *= 10;
        }
        while (x > 0) {
            int l = x / digit;
            int r = x % 10;
            if (l != r) return false;
            x = x % digit;      // every time x will reduce two digits
            x = x / 10;         // so if x < 10, it will become 0, but it is fine
            digit /= 100;       // since single digit is palindrome
        }
        return true;
    }
}
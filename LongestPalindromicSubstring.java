/*
Given a string S, find the longest palindromic substring in S. 
You may assume that the maximum length of S is 1000, and there exists one unique longest palindromic substring.
*/

// Solution 1: Brute force
// TC:O(N^3), SC:O(1)
// Time Limit Exceeded
public class Solution {
    public String longestPalindrome(String s) {
        if (s == null) return null;
        int len = s.length();
        while (len > 0) {
            for (int l = 0; l < s.length(); l++) {
                int r = l + len - 1;
                if (r >= s.length()) break;
                if (isPalindrome(s, l, r)) return s.substring(l, r + 1);
            }
            len--;
        }
        return "";
    }
    
    boolean isPalindrome(String s, int l, int r) {
        while (l <= r) {
            if (s.charAt(l) != s.charAt(r)) return false;
            l++;
            r--;
        }
        return true;
    }
}

// Solution 2: DP
// TC:O(N^2), SC:O(N^2)
// http://leetcode.com/2011/11/longest-palindromic-substring-part-i.html
public class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.isEmpty()) return s;
        boolean[][] dp = new boolean[s.length()][s.length()];
        int l = 0;
        int r = 0;
        // base case 1: every single char is palindrome
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = true;
        }
        // base case 2: check each two adjacent chars
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                l = i;
                r = i + 1;
            }
        }
        // check the remaining cases
        int len = 3;
        while (len <= s.length()) {
            for (int i = 0; i + len - 1 < s.length(); i++) {
                int j = i + len - 1;
                if (dp[i + 1][j - 1] && s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = true;
                    l = i;
                    r = j;
                }
            }
            len++;
        }
        return s.substring(l, r + 1);
    }
}

// Solution 3
// TC:O(N^2), SC:O(1)
// http://leetcode.com/2011/11/longest-palindromic-substring-part-i.html
public class Solution {
    public String longestPalindrome(String s) {
        if (s == null) return null;
        String result = "";
        for (int i = 0; i < s.length(); i++) {
            // if the length of palindrome is odd
            String res1 = getPalindrome(s, i, i);
            if (res1.length() > result.length()) {
                result = res1;
            }
            
            // if the length of palindrome is even
            String res2 = getPalindrome(s, i, i + 1);
            if (res2.length() > result.length()) {
                result = res2;
            }
        }
        return result;
    }
    
    String getPalindrome(String s, int l, int r) {
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        return s.substring(l + 1, r);     // attention to the boundary condition
    }
}

// Solution 4: Manacher’s Algorithm
// TC:O(N), SC:O(1)
// http://leetcode.com/2011/11/longest-palindromic-substring-part-ii.html
// http://www.felix021.com/blog/read.php?2040
public class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.isEmpty()) return s;
        String T = preProcess(s);
        int[] P = new int[T.length()];      // auxiliary counting array
        int C = 0;      // center of the mirror
        int R = 0;      // right bound of the mirror
        for (int i = 1; i < P.length - 1; i++) {
            int mirror = 2 * C - i;     // mirror of i
            P[i] = (R > i) ? Math.min(P[mirror], R - i) : 0;
            // continue checking
            while (T.charAt(i + P[i] + 1) == T.charAt(i - P[i] - 1)) {
                P[i]++;
            }
            // update C and R
            if (i + P[i] > R) {
                C = i;
                R = i + P[i];
            }
        }
        // get the longest palindrome
        int center = 0;
        int maxLen = 0;
        for (int i = 1; i < P.length - 1; i++) {
            if (P[i] > maxLen) {
                maxLen = P[i];
                center = i;
            }
        }
        return s.substring((center - maxLen - 1) / 2, (center + maxLen - 1) / 2);   // be careful here
    }
    
    String preProcess(String s) {
        StringBuffer sb = new StringBuffer("^");
        for (int i = 0; i < s.length(); i++) {
            sb.append("#");
            sb.append(s.charAt(i));
        }
        sb.append("#$");
        return sb.toString();
    }
}
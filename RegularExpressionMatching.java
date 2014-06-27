/*
Implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "a*") → true
isMatch("aa", ".*") → true
isMatch("ab", ".*") → true
isMatch("aab", "c*a*b") → true
*/


// Solution 1: Recursion
// Not concise enough, 'a**' is valid in this solution
public class Solution {
    public boolean isMatch(String s, String p) {
        if (s == p) return true;
        if (s == null || p == null) return false;
        if (p.isEmpty()) return s.isEmpty();
        
        if (p.charAt(0) == '*') return false;   // first char is '*'
        if (p.charAt(0) == '.') {
            if (p.length() > 1 && p.charAt(1) == '*') {     // followed by '*'
                p = skipStars(p.substring(1));
                for (int i = 0; i <= s.length(); i++) {
                    if (isMatch(s.substring(i), p)) return true;
                }
                return false;
            } else {
                if (s.isEmpty()) return false;
                return isMatch(s.substring(1), p.substring(1));
            }
        } else {    // current char is common char
            if (p.length() > 1 && p.charAt(1) == '*') {
                char c = p.charAt(0);
                p = skipStars(p.substring(1));
                for (int i = 0; i <= s.length(); i++) {
                    if (i != 0  && s.charAt(i - 1) != c) break;
                    if (isMatch(s.substring(i), p)) return true;
                }
                return false;
            } else {
                if (s.isEmpty()) return false;
                if (s.charAt(0) == p.charAt(0)) {
                    return isMatch(s.substring(1), p.substring(1));
                } else {
                    return false;
                }
            }
        }
    }
    
    String skipStars(String p) {
        int i = 0;
        while (i < p.length() && p.charAt(i) == '*') {
            i++;
        }
        return p.substring(i);
    }
}



// Solution 2: Recursion
// More concise, 'a**' is NOT valid in this solution
// http://leetcode.com/2011/09/regular-expression-matching.html
// http://www.programcreek.com/2012/12/leetcode-regular-expression-matching-in-java/
public class Solution {
    public boolean isMatch(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();
        
        // next is not '*'
        if (p.length() == 1 || p.charAt(1) != '*') {	// p's length = 1 is special case  
            if (s.isEmpty()) return false;
            if (p.charAt(0) != '.' && s.charAt(0) != p.charAt(0)) return false;
            return isMatch(s.substring(1), p.substring(1));
        }
        
        // next is '*'
        for (int i = 0; i <= s.length(); i++) {
            if (i != 0 && p.charAt(0) != '.' && s.charAt(i - 1) != p.charAt(0)) break;
            if (isMatch(s.substring(i), p.substring(2))) return true;
        }
        return false;
    }
}


// Solution 3: DP
// My own implementation, another reference link:
// http://codeganker.blogspot.com/2014/02/regular-expression-matching-leetcode.html
public class Solution {
    public boolean isMatch(String s, String p) {
        boolean[][] match = new boolean[p.length() + 1][s.length() + 1];
        match[0][0] = true;
        for (int i = 1; i <= p.length(); i++) {
            for (int j = 0; j <= s.length(); j++) {
                // if not follow by '*'
                if (i == p.length() || p.charAt(i) != '*') {
                    if (j == 0) continue;
                    if (match[i - 1][j - 1] && (p.charAt(i - 1) == s.charAt(j - 1) || p.charAt(i - 1) == '.')) {
                        match[i][j] = true;
                    }
                } else {    // follow by '*'
            		// match[i - 1][j]: repeat zero times
                	// match[i][j - 1]: repeat several times
                    if (match[i - 1][j] || (j > 0 && match[i][j - 1] && 
                        (p.charAt(i - 1) == s.charAt(j - 1) || p.charAt(i - 1) == '.'))) {
                        match[i][j] = true;
                        match[i + 1][j] = true;     // the layer of '*'
                    }
                }
            }
        }
        return match[p.length()][s.length()];
    }
}
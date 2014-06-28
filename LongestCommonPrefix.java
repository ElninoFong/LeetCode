/*
Write a function to find the longest common prefix string amongst an array of strings.
*/

// Solution 1
// Checking the longest common prefix char-by-char: 
// Get the char in the first string, compare it to every other strings in the corresponding position.
public class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        String s = strs[0];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            for (int j = 1; j < strs.length; j++) {
                String comp = strs[j];
                if (comp.length() <= i || comp.charAt(i) != c) return s.substring(0, i);
            }
        }
        return s;
    }
}


// Solution 2
// Checking the longest common prefix string-by-string: 
// Starting from the second string, compare it with the first string, and update the longest common suffix found so far. 
// This solution might be fast if the number of strings are much larger than the string lengths.
public class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        int prefixLen = strs[0].length();
        for (int i = 1; i < strs.length; i++) {
            prefixLen = Math.min(prefixLen, strs[i].length());
            for (int j = 0; j < prefixLen; j++) {
                if (strs[0].charAt(j) != strs[i].charAt(j)) {
                    prefixLen = j;
                }
            }
        }
        return strs[0].substring(0, prefixLen);
    }
}
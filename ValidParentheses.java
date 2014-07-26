/*
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
*/

// TC: O(n), SC: O(n)
// use stack
public class Solution {
    public boolean isValid(String s) {
        if (s == null || s.isEmpty()) return true;
        if (s.length() % 2 > 0) return false;
        char[] arr = s.toCharArray();
        Stack<Character> st = new Stack<Character>();
        for (char c : arr) {
            if (c == '(' || c == '{' || c == '[') {
                st.push(c);
            } else {
                if (st.isEmpty()) return false;
                char prev = st.pop();
                if ((c == ')' && prev != '(') || (c == '}' && prev != '{') 
                    || (c == ']' && prev != '[')) return false;
            }
        }
        return st.isEmpty();
    }
}
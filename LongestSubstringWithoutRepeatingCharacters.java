/*
Given a string, find the length of the longest substring without repeating characters. 
For example, the longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3. 
For "bbbbb" the longest substring is "b", with the length of 1.
*/
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        // TC:O(n), SC:O(n)
        if (s == null) return 0;
        HashMap<Character, Integer> hm = new HashMap<Character, Integer>();
        int max = 0, start = 0;
        for (int i = 0; i < s.length(); i++) {
            // only duplicate char appears after start is invalid
            if (!hm.containsKey(s.charAt(i)) || hm.get(s.charAt(i)) < start) {
                max = Math.max(max, i - start + 1);
            } else {
                start = hm.get(s.charAt(i)) + 1;
            }
            hm.put(s.charAt(i), i);
        }
        return max;
    }
}
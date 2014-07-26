/*
Given a digit string, return all possible letter combinations that the number could represent.
A mapping of digit to letters (just like on the telephone buttons) is given below.
mapping: http://goo.gl/TT6dch

Input:Digit string "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
Note:
Although the above answer is in lexicographical order, your answer could be in any order you want.
*/


// use trie?
// TC: O(m^n), SC: O(n). n is the length of digits, m is the length of letters mapping to one digit.
// DFS
public class Solution {
    public List<String> letterCombinations(String digits) {
        HashMap<Character, String> hm = new HashMap<Character, String>();
        hm.put('2', "abc");
        hm.put('3', "def");
        hm.put('4', "ghi");
        hm.put('5', "jkl");
        hm.put('6', "mno");
        hm.put('7', "pqrs");
        hm.put('8', "tuv");
        hm.put('9', "wxyz");
        
        List<String> result = new ArrayList<String>();
        if (digits == null || digits.isEmpty()) {
            result.add("");
            return result;
        }
        StringBuffer sb = new StringBuffer();
        letterCombinations(digits, result, sb, hm);
        return result;
    }
    
    void letterCombinations(String digits, List<String> result, StringBuffer sb, HashMap<Character, String> hm) {
        if (digits.isEmpty()) {
            result.add(sb.toString());
            return;
        }
        String s = hm.get(digits.charAt(0));
        for (char c : s.toCharArray()) {
            sb.append(c);
            letterCombinations(digits.substring(1), result, sb, hm);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
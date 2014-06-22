/*
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: 
(you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"
Write the code that will take a string and make this conversion given a number of rows:

string convert(string text, int nRows);
convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
*/

public class Solution {
    public String convert(String s, int nRows) {
        if (s == null || s.isEmpty() || nRows <= 1) return s;
        StringBuffer sb = new StringBuffer();
        for (int curRow = 0; curRow < nRows; curRow++) {
            int i = curRow;
            while (i < s.length()) {
                sb.append(s.charAt(i));
                if (curRow != 0 && curRow != nRows - 1) {
                    int j = i + (nRows - curRow - 1) * 2;
                    if (j < s.length()) {
                        sb.append(s.charAt(j));
                    }
                }
                i += (2 * nRows - 2);
            }
        }
        return sb.toString();
    }
}
/*
Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? 
Find all unique triplets in the array which gives the sum of zero.

Note:
Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
The solution set must not contain duplicate triplets.
    For example, given array S = {-1 0 1 2 -1 -4},

    A solution set is:
    (-1, 0, 1)
    (-1, -1, 2)
*/

// http://www.programcreek.com/2012/12/leetcode-3sum/
// http://leetcode.com/2010/04/finding-all-unique-triplets-that-sums.html
public class Solution {
    public List<List<Integer>> threeSum(int[] num) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Arrays.sort(num);
        for (int i = 0; i < num.length - 2; i++) {
            if (i > 0 && num[i] == num[i - 1]) continue;
            int start = i + 1;
            int end = num.length - 1;
            while (start < end) {
                if (num[start] + num[end] == -num[i]) {
                    List<Integer> res = new ArrayList<Integer>();
                    res.add(num[i]);
                    res.add(num[start]);
                    res.add(num[end]);
                    result.add(res);
                    start++;
                    end--;
                    while (start < end && num[start] == num[start - 1]) {
                        start++;
                    }
                    while (start < end && num[end] == num[end + 1]) {
                        end--;
                    }
                } else if (num[start] + num[end] < -num[i]) {
                    start++;
                } else {
                    end--;
                }
            }
        }
        return result;
    }
}
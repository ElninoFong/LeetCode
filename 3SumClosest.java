/*
Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. 
Return the sum of the three integers. You may assume that each input would have exactly one solution.

    For example, given array S = {-1 2 1 -4}, and target = 1.

    The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
*/


// TC: O(n^2), SC: O(1)
public class Solution {
    public int threeSumClosest(int[] num, int target) {
        Arrays.sort(num);
        int res = 0;
        int diff = Integer.MAX_VALUE;
        for (int i = 0; i < num.length - 2; i++) {
            int start = i + 1;
            int end = num.length - 1;
            while (start < end) {
                int sum = num[i] + num[start] + num[end];
                if (sum == target) return target;
                if (Math.abs(sum - target) < diff) {
                    diff = Math.abs(sum - target);
                    res = sum;
                }
                if (sum < target) {
                    start++;
                } else {
                    end--;
                }
            }
        }
        return res;
    }
}
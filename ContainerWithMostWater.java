/*
Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). 
n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). 
Find two lines, which together with x-axis forms a container, such that the container contains the most water.

Note: You may not slant the container.
*/


// Solution 1: TC: O(nlgn), SC: O(n)
// But the hash map can not store duplicated keys, so this solution requires distinct heights
// http://tech-wonderland.net/blog/leetcode-container-with-most-water.html
public class Solution {
    public int maxArea(int[] height) {
        if (height.length < 2) return 0;
        HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();     // pair: < height[i], i >
        for (int i = 0; i < height.length; i++) {
            hm.put(height[i], i);
        }
        Arrays.sort(height);
        int maxIndex = hm.get(height[height.length - 1]);   // set maxIndex and minIndex to the largest value
        int minIndex = maxIndex;
        int maxWater = 0;
        for (int i = height.length - 2; i >= 0; i--) {
            int currIndex = hm.get(height[i]);
            int indexDiff = Math.max(Math.abs(currIndex - maxIndex), Math.abs(currIndex - minIndex));
            maxWater = Math.max(maxWater, height[i] * indexDiff);   // current height is less than others on the right
            maxIndex = Math.max(maxIndex, currIndex);
            minIndex = Math.min(minIndex, currIndex);
        }
        return maxWater;
    }
}


// Solution 2: TC: O(n), SC: O(1)
// http://www.cnblogs.com/lichen782/p/leetcode_Container_With_Most_Water.html
public class Solution {
    public int maxArea(int[] height) {
        int max = 0;
        int i = 0;
        int j = height.length - 1;
        while (i < j) {
            max = Math.max(max, Math.min(height[i], height[j]) * (j - i));
            if (height[i] <= height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return max;
    }
}
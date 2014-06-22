/*
Given an array of integers, find two numbers such that they add up to a specific target number.
The function twoSum should return indices of the two numbers such that they add up to the target, 
where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.
You may assume that each input would have exactly one solution.
Input: numbers={2, 7, 11, 15}, target=9
Output: index1=1, index2=2	
*/

public class Solution {
    public int[] twoSum(int[] numbers, int target) {
        // TC:O(N), SC:O(N)
        int[] result = new int[2];
        HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>(); // hashmap -> <num, index>
        for (int i = 0; i < numbers.length; i++) {
            if (hm.containsKey(target - numbers[i])) {
                result[0] = hm.get(target - numbers[i]) + 1;    // non zero based
                result[1] = i + 1;
                break;
            }
            hm.put(numbers[i], i);  // store after check, otherwise may get the same index twice, like {2, 3} target = 4
        }
        return result;
    }
}
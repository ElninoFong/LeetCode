/*
There are two sorted arrays A and B of size m and n respectively. 
Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
*/

public class Solution {
    public double findMedianSortedArrays(int A[], int B[]) {
        // O(m + n) solution
        int m = A.length, n = B.length;
        if (m == 0 && n == 0) return 0;
        if ((m + n) % 2 != 0) {
            return (double)findKth(A, B, (m + n) / 2 + 1);
        } else {
            return ((double)findKth(A, B, (m + n) / 2) + (double)findKth(A, B, (m + n) / 2 + 1)) / 2.0;
        }
    }
    
    int findKth(int A[], int B[], int K) {
        int res = 0;
        int i = 0, j = 0;
        while (K > 0) {
            if (i == A.length) {
                return B[j + K - 1];
            }
            if (j == B.length) {
                return A[i + K - 1];
            }
            if (A[i] <= B[j]) {
                res = A[i++];
            } else {
                res = B[j++];
            }
            K--;
        }
        return res;
    }
}

public class Solution {
    public double findMedianSortedArrays(int A[], int B[]) {
        // O(log(m + n)) solution
        if (A == null || B == null) return -1;
        if (A.length == 0 && B.length == 0) return -1;
        if ((A.length + B.length) % 2 > 0) {
            return findKth(A, 0, A.length, B, 0, B.length, (A.length + B.length) / 2 + 1);
        } else {
            return (findKth(A, 0, A.length, B, 0, B.length, (A.length + B.length) / 2) +
                   findKth(A, 0, A.length, B, 0, B.length, (A.length + B.length) / 2 + 1)) / 2;
        }
    }
    
    double findKth(int A[], int startA, int endA, int B[], int startB, int endB, int k) {
        int lenA = endA - startA;
        int lenB = endB - startB;
        if (lenA > lenB) return findKth(B, startB, endB, A, startA, endA, k);
        
        if (lenA < 1) return B[startB + k - 1];     // A is empty
        if (k == 1) return Math.min(A[startA], B[startB]);      // base case
        int midA = Math.min(k / 2, lenA);
        int midB = k - midA;
        int indexA = startA + midA - 1;     // index in the array
        int indexB = startB + midB - 1;
        
        if (A[indexA] == B[indexB]) {
            return A[indexA];
        } else if (A[indexA] > B[indexB]) {
            return findKth(A, startA, indexA + 1, B, indexB + 1, endB, k - midB);   // be careful of the boundary conditions
        } else {
            return findKth(A, indexA + 1, endA, B, startB, indexB + 1, k - midA);
        }
    }
}
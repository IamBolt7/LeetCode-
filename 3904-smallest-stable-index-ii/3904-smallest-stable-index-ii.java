class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;

        // suffixMin[i] = min(nums[i..n-1]), built right to left
        int[] suffixMin = new int[n];
        suffixMin[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixMin[i] = Math.min(suffixMin[i + 1], nums[i]);
        }

        // Sweep left to right, tracking prefix max with a single variable
        // instead of a full array, so we only need O(n) extra space total
        // (not O(n) for two separate arrays).
        int prefixMax = nums[0];
        for (int i = 0; i < n; i++) {
            prefixMax = Math.max(prefixMax, nums[i]);
            if (prefixMax - suffixMin[i] <= k) {
                return i;
            }
        }

        return -1;
    }
}
class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        int minIdx = 0, maxIdx = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] < nums[minIdx]) minIdx = i;
            if (nums[i] > nums[maxIdx]) maxIdx = i;
        }

        int i = Math.min(minIdx, maxIdx);
        int j = Math.max(minIdx, maxIdx);

        int bothFromFront = j + 1;              // remove up through the later index
        int bothFromBack = n - i;                // remove from the earlier index to the end
        int oneEach = (i + 1) + (n - j);         // earlier one from front, later one from back

        return Math.min(bothFromFront, Math.min(bothFromBack, oneEach));
    }
}
class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) idx[i] = i;
        Arrays.sort(idx, (a, b) -> nums[a] - nums[b]);

        int[] result = new int[n];
        int i = 0;
        while (i < n) {
            int j = i;
            // Extend the group while consecutive sorted values are within `limit`
            while (j + 1 < n && nums[idx[j + 1]] - nums[idx[j]] <= limit) {
                j++;
            }
            // Positions in this group, sorted ascending
            int[] positions = new int[j - i + 1];
            for (int k = i; k <= j; k++) positions[k - i] = idx[k];
            Arrays.sort(positions);

            // Assign sorted values (already sorted by construction) to sorted positions
            for (int k = 0; k <= j - i; k++) {
                result[positions[k]] = nums[idx[i + k]];
            }
            i = j + 1;
        }
        return result;
    }
}
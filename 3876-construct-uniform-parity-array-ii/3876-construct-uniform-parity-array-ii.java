class Solution {
    public boolean uniformArray(int[] nums1) {
        int min = nums1[0];
        boolean hasOdd = false;

        for (int x : nums1) {
            if (x < min) {
                min = x;
            }
            if (Math.abs(x) % 2 != 0) {
                hasOdd = true;
            }
        }

        // If there are no odd numbers, all are even -> true
        if (!hasOdd) {
            return true;
        }

        // If there is at least one odd number, min must be odd
        return Math.abs(min) % 2 != 0;
    }
}
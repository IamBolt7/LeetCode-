class Solution {
    public boolean uniformArray(int[] nums1) {
        int countOdd = 0, countEven = 0;
        for (int x : nums1) {
            if (x % 2 != 0) countOdd++;
            else countEven++;
        }

        boolean allEvenPossible = (countOdd != 1);

        boolean allOddPossible = (countOdd >= 1) || (countEven == 0);

        return allEvenPossible || allOddPossible;
    }
}
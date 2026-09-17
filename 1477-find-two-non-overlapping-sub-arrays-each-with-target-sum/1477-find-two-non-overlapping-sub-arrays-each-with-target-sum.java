class Solution {
    public int minSumOfLengths(int[] arr, int target) {
       int n = arr.length;
        int[] minLenEndingAtOrBefore = new int[n];
        Arrays.fill(minLenEndingAtOrBefore, Integer.MAX_VALUE);

        int left = 0;
        int sum = 0;
        int best = Integer.MAX_VALUE;
        int result = Integer.MAX_VALUE;
        int bestSoFar = Integer.MAX_VALUE; // shortest valid sub-array length ending at index <= right

        for (int right = 0; right < n; right++) {
            sum += arr[right];

            while (sum > target) {
                sum -= arr[left];
                left++;
            }

            if (sum == target) {
                int currLen = right - left + 1;

                // if there was a valid sub-array ending before 'left', we can combine
                if (left > 0 && minLenEndingAtOrBefore[left - 1] != Integer.MAX_VALUE) {
                    result = Math.min(result, minLenEndingAtOrBefore[left - 1] + currLen);
                }

                bestSoFar = Math.min(bestSoFar, currLen);
            }

            minLenEndingAtOrBefore[right] = bestSoFar;
        }

        return result == Integer.MAX_VALUE ? -1 : result;
         
    }
}
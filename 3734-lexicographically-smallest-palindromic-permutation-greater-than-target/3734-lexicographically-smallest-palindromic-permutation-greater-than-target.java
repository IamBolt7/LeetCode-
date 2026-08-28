class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();

        // Count characters in s
        int[] count = new int[26];

        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        // A palindrome can have at most one character with odd frequency
        int oddCount = 0;
        char middle = 0;

        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 == 1) {
                oddCount++;
                middle = (char) ('a' + i);
            }
        }

        if (oddCount > 1) {
            return "";
        }

        // Build counts for the left half
        int[] halfCount = new int[26];

        for (int i = 0; i < 26; i++) {
            halfCount[i] = count[i] / 2;
        }

        int halfLength = n / 2;

        // The first half of target
        String targetHalf = target.substring(0, halfLength);

        /*
         * First check whether targetHalf itself can be used
         * to create a palindrome.
         */
        int[] remaining = halfCount.clone();
        boolean possible = true;

        for (char c : targetHalf.toCharArray()) {
            int index = c - 'a';

            if (remaining[index] == 0) {
                possible = false;
                break;
            }

            remaining[index]--;
        }

        // If target itself can be matched in the first half,
        // construct the palindrome and check if it is greater.
        if (possible) {
            String palindrome =
                    targetHalf
                    + (oddCount == 1 ? String.valueOf(middle) : "")
                    + new StringBuilder(targetHalf).reverse();

            if (palindrome.compareTo(target) > 0) {
                return palindrome;
            }
        }

        /*
         * We now need the smallest half-string that is
         * lexicographically greater than targetHalf.
         *
         * We try to keep the prefix equal to targetHalf
         * and increase one character.
         */
        remaining = halfCount.clone();

        String bestHalf = null;

        for (int i = 0; i < halfLength; i++) {

            int targetChar = targetHalf.charAt(i) - 'a';

            // Try replacing targetHalf[i] with the smallest
            // available character greater than it.
            for (int c = targetChar + 1; c < 26; c++) {

                if (remaining[c] > 0) {

                    int[] nextRemaining = remaining.clone();
                    nextRemaining[c]--;

                    StringBuilder candidate = new StringBuilder();

                    // Prefix equal to targetHalf
                    candidate.append(targetHalf, 0, i);

                    // Character that makes it greater
                    candidate.append((char) ('a' + c));

                    // Put remaining characters in sorted order
                    for (int j = 0; j < 26; j++) {
                        for (int k = 0; k < nextRemaining[j]; k++) {
                            candidate.append((char) ('a' + j));
                        }
                    }

                    bestHalf = candidate.toString();
                    break;
                }
            }

            // Continue matching targetHalf prefix
            if (remaining[targetChar] == 0) {
                break;
            }

            remaining[targetChar]--;
        }

        // No greater permutation exists
        if (bestHalf == null) {
            return "";
        }

        // Construct the palindrome
        StringBuilder answer = new StringBuilder();

        answer.append(bestHalf);

        if (oddCount == 1) {
            answer.append(middle);
        }

        answer.append(new StringBuilder(bestHalf).reverse());

        return answer.toString();
    }
}
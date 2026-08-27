class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        for (char c : s.toCharArray()) count[c - 'a']++;

        int bestPos = -1;
        char bestChar = 0;
        int[] bestCount = null;

        for (int i = 0; i < n; i++) {
            char tc = target.charAt(i);

            // Try to place a character strictly greater than tc here
            for (char c = (char) (tc + 1); c <= 'z'; c++) {
                if (count[c - 'a'] > 0) {
                    bestPos = i;
                    bestChar = c;
                    bestCount = count.clone();
                    bestCount[c - 'a']--;
                    break;
                }
            }

            // Try to extend the exact-match prefix with target[i]
            if (count[tc - 'a'] > 0) {
                count[tc - 'a']--;
            } else {
                break; // can't match target any further
            }
        }

        if (bestPos == -1) return "";

        StringBuilder sb = new StringBuilder();
        sb.append(target, 0, bestPos);
        sb.append(bestChar);
        for (char c = 'a'; c <= 'z'; c++) {
            for (int k = 0; k < bestCount[c - 'a']; k++) sb.append(c);
        }
        return sb.toString();
    }
}
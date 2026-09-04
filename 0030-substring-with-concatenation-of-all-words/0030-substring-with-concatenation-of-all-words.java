import java.util.*;

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();

        int wordLen = words[0].length();
        int wordCount = words.length;

        Map<String, Integer> required = new HashMap<>();

        for (String word : words) {
            required.put(word, required.getOrDefault(word, 0) + 1);
        }

        for (int start = 0; start < wordLen; start++) {

            int left = start;
            int right = start;
            int count = 0;

            Map<String, Integer> current = new HashMap<>();

            while (right + wordLen <= s.length()) {

                String word = s.substring(right, right + wordLen);
                right += wordLen;

                if (required.containsKey(word)) {

                    current.put(word,
                        current.getOrDefault(word, 0) + 1);

                    count++;

                    while (current.get(word) > required.get(word)) {

                        String leftWord =
                            s.substring(left, left + wordLen);

                        current.put(leftWord,
                            current.get(leftWord) - 1);

                        left += wordLen;
                        count--;
                    }

                    if (count == wordCount) {
                        result.add(left);

                        String leftWord =
                            s.substring(left, left + wordLen);

                        current.put(leftWord,
                            current.get(leftWord) - 1);

                        left += wordLen;
                        count--;
                    }

                } else {
                    current.clear();
                    count = 0;
                    left = right;
                }
            }
        }

        return result;
    }
}
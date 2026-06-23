import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day2CountSignatureForcedPass {

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] freq = new int[26];
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a']++;
            freq[t.charAt(i) - 'a']--;
        }

        for (int count : freq) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> groups = new HashMap<>();
        for (String s : strs) {
            String key = buildCountKey(s);
            groups.computeIfAbsent(key, ignored -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(groups.values());
    }

    private String buildCountKey(String s) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }
        return Arrays.toString(freq);
    }

    public int numSubarraysWithSum(int[] nums, int goal) {
        Map<Integer, Integer> count = new HashMap<>();
        count.put(0, 1);

        int prefix = 0;
        int ans = 0;
        for (int num : nums) {
            prefix += num;
            ans += count.getOrDefault(prefix - goal, 0);
            count.put(prefix, count.getOrDefault(prefix, 0) + 1);
        }
        return ans;
    }
}

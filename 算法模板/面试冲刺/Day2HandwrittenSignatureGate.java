import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day2HandwrittenSignatureGate {

    public int[] signatureForLowercase(String s) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }
        return freq;
    }

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        return Arrays.equals(signatureForLowercase(s), signatureForLowercase(t));
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> groups = new HashMap<>();
        for (String s : strs) {
            String key = buildCountKey(s);
            groups.computeIfAbsent(key, ignored -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(groups.values());
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

    private String buildCountKey(String s) {
        return Arrays.toString(signatureForLowercase(s));
    }
}

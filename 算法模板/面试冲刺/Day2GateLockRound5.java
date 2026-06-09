import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day2GateLockRound5 {

    // Equal letter multisets must map to the same serialized count vector.
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> groups = new HashMap<>();
        for (String s : strs) {
            int[] freq = new int[26];
            for (char c : s.toCharArray()) {
                freq[c - 'a']++;
            }
            String key = Arrays.toString(freq);
            groups.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(groups.values());
    }

    // Fixed lowercase alphabet lets us count directly in O(1) extra space.
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }
        for (char c : t.toCharArray()) {
            freq[c - 'a']--;
        }
        for (int count : freq) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }

    // Cover one target string by spending magazine counts.
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] freq = new int[26];
        for (char c : magazine.toCharArray()) {
            freq[c - 'a']++;
        }
        for (char c : ransomNote.toCharArray()) {
            if (--freq[c - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

    // Intersect counts by keeping the minimum frequency across words.
    public List<String> commonChars(String[] words) {
        int[] best = new int[26];
        Arrays.fill(best, Integer.MAX_VALUE);

        for (String word : words) {
            int[] freq = new int[26];
            for (char c : word.toCharArray()) {
                freq[c - 'a']++;
            }
            for (int i = 0; i < 26; i++) {
                best[i] = Math.min(best[i], freq[i]);
            }
        }

        List<String> ans = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            for (int count = 0; count < best[i]; count++) {
                ans.add(String.valueOf((char) ('a' + i)));
            }
        }
        return ans;
    }

    // Only sequence starts expand, so each value is consumed once overall.
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int best = 0;
        for (int num : set) {
            if (set.contains(num - 1)) {
                continue;
            }
            int end = num;
            while (set.contains(end)) {
                end++;
            }
            best = Math.max(best, end - num);
        }
        return best;
    }

    // Query prefix - k before inserting the current prefix.
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        count.put(0, 1);

        int prefix = 0;
        int ans = 0;
        for (int num : nums) {
            prefix += num;
            ans += count.getOrDefault(prefix - k, 0);
            count.put(prefix, count.getOrDefault(prefix, 0) + 1);
        }
        return ans;
    }

    // Earliest repeated balance yields the longest equal-zero-one span.
    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> first = new HashMap<>();
        first.put(0, -1);

        int balance = 0;
        int best = 0;
        for (int i = 0; i < nums.length; i++) {
            balance += nums[i] == 1 ? 1 : -1;
            if (first.containsKey(balance)) {
                best = Math.max(best, i - first.get(balance));
            } else {
                first.put(balance, i);
            }
        }
        return best;
    }

    // Repeated normalized modulo means the subarray sum is divisible by k.
    public int subarraysDivByK(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        count.put(0, 1);

        int prefix = 0;
        int ans = 0;
        for (int num : nums) {
            prefix += num;
            int mod = ((prefix % k) + k) % k;
            ans += count.getOrDefault(mod, 0);
            count.put(mod, count.getOrDefault(mod, 0) + 1);
        }
        return ans;
    }
}

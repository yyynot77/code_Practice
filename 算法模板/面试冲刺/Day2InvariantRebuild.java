import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day2InvariantRebuild {

    // Equivalent letter multisets must share one stable serialized key.
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

    // Fixed lowercase alphabet lets us compare by counts in O(1) extra space.
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] diff = new int[26];
        for (int i = 0; i < s.length(); i++) {
            diff[s.charAt(i) - 'a']++;
            diff[t.charAt(i) - 'a']--;
        }
        for (int value : diff) {
            if (value != 0) {
                return false;
            }
        }
        return true;
    }

    // Cover the target by spending counts from the source string.
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
            int current = num;
            int length = 1;
            while (set.contains(current + 1)) {
                current++;
                length++;
            }
            best = Math.max(best, length);
        }
        return best;
    }

    // Query prefix - k before inserting the current prefix count.
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

    // The earliest repeated balance gives the longest valid span.
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

    // Repeated normalized modulo means the subarray difference is divisible by k.
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

    // Same prefix-frequency invariant as 560, specialized to binary arrays.
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

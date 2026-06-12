import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day2OralProofFirst {

    // Same multiset of letters must map to one canonical serialized key.
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

    // Fixed lowercase alphabet lets counts replace sorting.
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

    // Spend magazine counts while building the ransom note.
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

    // Only expand from starts; every number is consumed at most once overall.
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

    // Count prior prefixes equal to currentPrefix - k before inserting currentPrefix.
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

    // The earliest repeated balance produces the longest valid interval.
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

    // Normalized equal modulo states imply a divisible subarray difference.
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

    // Same prefix-frequency template as 560, specialized to binary arrays.
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

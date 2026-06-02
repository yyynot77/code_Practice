import java.util.*;

public class HashMapTemplates {

    // 1) 值到下标映射: Two Sum 类
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int need = target - nums[i];
            if (indexMap.containsKey(need)) {
                return new int[] { indexMap.get(need), i };
            }
            indexMap.put(nums[i], i);
        }
        return new int[0];
    }

    // 2) 稳定 key 分组: Group Anagrams 类
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> groups = new HashMap<>();
        for (String s : strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            groups.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(groups.values());
    }

    // 3) 去重 + 只从起点扩展: Longest Consecutive Sequence 类
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int x : nums) {
            set.add(x);
        }

        int best = 0;
        for (int x : set) {
            if (!set.contains(x - 1)) {
                int y = x;
                while (set.contains(y)) {
                    y++;
                }
                best = Math.max(best, y - x);
            }
        }
        return best;
    }
}

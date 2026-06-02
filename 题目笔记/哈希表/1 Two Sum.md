# 1. Two Sum

官方链接：[1. Two Sum](https://leetcode.com/problems/two-sum/)

## 一句话结论

这题不是“找两个数”，而是“遍历当前数时，立刻查询它的互补值是否已经出现过”。

## 正确思路

用哈希表记录已经出现过的数字和对应下标。

```java
import java.util.HashMap;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int need = target - nums[i];

            if (map.containsKey(need)) {
                return new int[]{map.get(need), i};
            }

            map.put(nums[i], i);
        }

        return null;
    }
}
```

## 为什么这样写

- `map` 记录之前出现过的数字和对应下标。
- 当前值 `nums[i]` 只需要找 `target - nums[i]`。
- 找到后直接返回，不需要继续遍历。
- 一遍写法要先查再放，避免当前元素和自己配对。

## 两遍写法

```java
import java.util.HashMap;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        for (int j = 0; j < nums.length; j++) {
            int need = target - nums[j];
            if (map.containsKey(need) && map.get(need) != j) {
                return new int[]{j, map.get(need)};
            }
        }

        return null;
    }
}
```

两遍写法需要额外判断：

```java
map.get(need) != j
```

原因是不能让同一个下标被使用两次。

## 容易犯错

- `HashMap` 写成 `Hashmap`。
- `containsKey` 写成 `containKey`。
- 把 `map.get(need)` 当成 boolean 使用。
- 把 `map.get(need)` 和 `nums[j]` 比较，混淆了下标和值。
- `new int[]{a, b}` 写成 `new int[2]{a, b}`。
- `map.get(need)` 写成 `map.get[need]`。
- 用 `nums.length < 0` 作为兜底条件，但数组长度不可能小于 0。

## 复杂度

- 时间复杂度：`O(n)`
- 空间复杂度：`O(n)`

## 面试复述

我用一个哈希表记录已经遍历过的数字和它们的下标。遍历到当前数字时，计算它需要的互补值 `target - nums[i]`，如果互补值已经在哈希表中，就返回之前的下标和当前下标；如果不在，就把当前数字和下标放进哈希表，留给后面的元素匹配。

## 复盘提问

- 为什么一遍写法必须先查再放？
- 两遍写法为什么要判断 `map.get(need) != j`？
- 如果数组是 `[3, 3]`，目标值是 `6`，代码会返回什么？
- 如果题目没有保证一定有答案，返回 `null` 会有什么潜在问题？
- 暴力解法复杂度是多少？哈希表为什么能优化？


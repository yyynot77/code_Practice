# 1. Two Sum

官方链接: [1. Two Sum](https://leetcode.com/problems/two-sum/)

## 一句话结论

这题不是“找两个数”，而是“遍历当前数时，立刻查它的互补值是否已经出现”。

## 正确思路

```java
Map<Integer, Integer> map = new HashMap<>();
for (int i = 0; i < nums.length; i++) {
    int need = target - nums[i];
    if (map.containsKey(need)) {
        return new int[] { map.get(need), i };
    }
    map.put(nums[i], i);
}
```

## 为什么这样写

- `map` 记录之前出现过的数字和对应下标
- 当前值 `nums[i]` 只需要找 `target - nums[i]`
- 找到就直接返回，不需要继续枚举
- 必须先查再放，避免当前元素和自己配对

## 容易犯错

- 先插入当前值再查询
- 返回元素值而不是索引
- 忘记题目只要求一组答案

## 复杂度

- 时间: `O(n)`
- 空间: `O(n)`

## 复盘提问

- 为什么必须先查再放?
- 如果数组里有重复值，这个写法为什么仍然正确?

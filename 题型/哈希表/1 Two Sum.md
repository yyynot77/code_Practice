# 1. Two Sum

- 难度：Easy
- 模式：哈希表查互补值
- 结论：一次遍历，先查后放

## 模板识别

- 看到“是否存在两个数满足某个和”。
- 看到“返回下标”。
- 暴力是两层循环，通常可以用哈希表降到 `O(n)`。

## 题解骨架

```java
HashMap<Integer, Integer> map = new HashMap<>();

for (int i = 0; i < nums.length; i++) {
    int need = target - nums[i];
    if (map.containsKey(need)) {
        return new int[]{map.get(need), i};
    }
    map.put(nums[i], i);
}
```

## 容易错的点

- 先 `put` 再 `check`，导致当前元素可能和自己配对。
- 返回元素值而不是下标。
- 忘记题目只需要返回一组答案。
- `HashMap`、`containsKey`、`new int[]{...}` 这些 Java 语法写错。

## 面试复述

哈希表保存“之前遍历过的数及其下标”。每到一个新数，先看它的互补值是否已经出现。如果出现，就返回两个下标。因为每个元素只处理一次，所以时间复杂度是 `O(n)`。

## 复盘检查

- 能否不看答案写出一遍哈希表模板？
- 能否解释为什么必须先查后放？
- 能否说清楚时间复杂度和空间复杂度？


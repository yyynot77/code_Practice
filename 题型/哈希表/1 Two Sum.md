# 1. Two Sum

- 难度: Easy
- 模式: 哈希表查互补值
- 结论: 一次遍历，先查后放

## 模板识别

- 看到“是否存在两个数满足条件”
- 看到“返回下标”
- 暴力是两层循环，目标通常是降到 `O(n)`

## 题解骨架

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

## 容易错的点

- 先 `put` 再 `check`
- 返回元素值而不是下标
- 忘记题目只需要一组答案

## 面试复述

- 哈希表保存“之前遍历过的数及其下标”
- 每到一个新数，先看它的互补值是否已经出现
- 因为每个元素只处理一次，所以时间复杂度是 `O(n)`

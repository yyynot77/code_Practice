# 128. Longest Consecutive Sequence

- 难度: Medium
- 模式: `HashSet` 去重 + 只从序列起点扩展
- 结论: 起点判断是把复杂度压到 `O(n)` 的核心

## 模板识别

- 看到“连续序列”
- 看到“要求 `O(n)`”
- 排序能做但不达标, 需要集合去重

## 题解骨架

```java
Set<Integer> set = new HashSet<>();
for (int x : nums) set.add(x);

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
```

## 容易错的点

- 对每个点都扩展, 写成 `O(n^2)`
- 忽略去重
- 只会背代码, 不会证明每个元素最多被访问一次

## 面试复述

- 如果 `x - 1` 不存在, `x` 才可能是一段连续序列的起点
- 从起点开始扩展不会漏答案
- 每个元素只属于某一段扩展过程一次, 所以总复杂度是 `O(n)`

# 128. Longest Consecutive Sequence

官方链接: [128. Longest Consecutive Sequence](https://leetcode.com/problems/longest-consecutive-sequence/)

## 一句话结论

这题的关键不是“向后扩展”, 而是“只从连续段起点扩展”。

## 正确思路

```java
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
```

## 为什么这样写

- `set` 先去重并支持 `O(1)` 查询
- 只有当 `x - 1` 不存在时, `x` 才是连续段开头
- 从起点出发, 每个元素最多被某一段扫描一次

## 容易犯错

- 每个元素都开一次 `while`
- 忘记集合去重
- 不会证明整体 `O(n)`

## 复杂度

- 时间: `O(n)`
- 空间: `O(n)`

## 复盘提问

- 为什么 `x - 1` 不存在就能确定 `x` 是起点?
- `while` 看起来是嵌套循环, 为什么总复杂度不是 `O(n^2)`?

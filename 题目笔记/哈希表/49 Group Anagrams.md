# 49. Group Anagrams

官方链接: [49. Group Anagrams](https://leetcode.com/problems/group-anagrams/)

## 一句话结论

这题核心不是遍历, 而是给每个字符串构造一个“同组必相同、异组必不同”的稳定 key。

## 正确思路

```java
Map<String, List<String>> groups = new HashMap<>();
for (String s : strs) {
    char[] chars = s.toCharArray();
    Arrays.sort(chars);
    String key = new String(chars);
    groups.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
}
return new ArrayList<>(groups.values());
```

## 为什么这样写

- 异位词排序后会得到同一个字符串
- 这个排序后字符串就是稳定 key
- 哈希表把同一个 key 的字符串收集到同一个桶里

## 容易犯错

- 知道“排序”但无法解释原理
- 计数 key 写法不固定, 造成同类字符串 key 不同
- 忘记初始化列表

## 复杂度

- 时间: `O(n * k log k)`
- 空间: `O(n * k)`

## 复盘提问

- 为什么排序后字符串一定能作为 key?
- 如果要把复杂度优化到不用排序, 你会怎样设计 26 位计数 key?

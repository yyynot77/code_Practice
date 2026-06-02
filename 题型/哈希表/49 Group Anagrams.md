# 49. Group Anagrams

- 难度: Medium
- 模式: 哈希分组 + 稳定 key 设计
- 结论: 同一组异位词必须映射到完全一致的 key

## 模板识别

- 看到“按某种特征分组”
- 关键难点不是遍历, 而是 key 如何构造

## 稳定写法

```java
Map<String, List<String>> groups = new HashMap<>();
for (String s : strs) {
    char[] chars = s.toCharArray();
    Arrays.sort(chars);
    String key = new String(chars);
    groups.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
}
```

## 容易错的点

- 只记住“排序”但说不清为什么排序后能分组
- 自己设计计数 key 时格式不稳定
- 忘记 `computeIfAbsent`

## 面试复述

- 异位词的本质是字符频次一致
- 排序后字符串一致, 所以可以作为稳定 key
- 这是“构造签名再分组”的经典哈希题

# 49. Group Anagrams

- 难度: Medium
- 模式: 哈希分组 + 计数签名 stable key
- 结论: 同一组异位词必须映射到完全一致的 canonical count signature

## 模板识别

- 看到“按某种字符特征分组”
- 关键不在遍历，而在如何构造一个稳定且可复用的 key
- 今天的训练要求是把它和 `242. Valid Anagram` 视为同一模板家族

## 稳定写法

```java
Map<String, List<String>> groups = new HashMap<>();
for (String s : strs) {
    int[] freq = new int[26];
    for (char c : s.toCharArray()) {
        freq[c - 'a']++;
    }
    String key = Arrays.toString(freq);
    groups.computeIfAbsent(key, ignored -> new ArrayList<>()).add(s);
}
```

## 容易错的点

- 只会说“分组”却说不清 key 到底表示什么
- 把排序 key 当成默认答案，跳过今天要强化的计数签名模板
- 自己设计计数 key 时格式不稳定
- 忘记 `computeIfAbsent`

## 面试复述

- 异位词的本质是 26 个字符计数完全一致
- 所以可以把计数数组当作 canonical signature
- `49` 不是新模式，而是把 `242` 的签名从“判等”扩展到“分组”

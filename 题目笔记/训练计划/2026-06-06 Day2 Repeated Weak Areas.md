# 2026-06-06 Day 2 Repeated Weak Areas

## Training Position

- Plan slot: Day 2 / 30
- Current status: blocked
- Escalation level: repeated weak areas
- Reason: The Day 2 gate from 2026-06-04 still has no completion evidence. `49` and `128` now move from ordinary remediation into repeated-weak-area handling.
- Objective: Stabilize two hash-table families under interview timing pressure before unlocking more pointer work.

## Today's Focus Pack

| Layer | Problem | Pattern | Pass standard |
| --- | --- | --- | --- |
| Gate A1 | 49. Group Anagrams | Stable key design | 12 min AC + explain sorted key vs count key |
| Gate A2 | 242. Valid Anagram | Fixed alphabet counting | 6 min AC + rewrite counting template |
| Gate B1 | 128. Longest Consecutive Sequence | HashSet + sequence start expansion | 12 min AC + prove `O(n)` clearly |
| Gate B2 | 560. Subarray Sum Equals K | Prefix sum + hash frequency | 15 min AC + explain why negatives break sliding window |
| Release 1 | 167. Two Sum II | Sorted opposite pointers | 8 min AC after all gates pass |
| Release 2 | 11. Container With Most Water | Move shorter wall | 15 min AC after all gates pass |
| Release 3 | 15. 3Sum | Sort + fix + two pointers + dedup | 25 min AC after all gates pass |

## Execution Order

1. Finish `49` and `242` back-to-back. Treat them as one equivalence-representation block.
2. Finish `128` and `560` back-to-back. Treat them as one hash-lookup and counting block.
3. Unlock `167`, `11`, and `15` only after all four gate tasks are complete without editorial dependence.

## Must-Say Template Lines

- `49`: "A stable key means equivalent strings always map to the same representation."
- `242`: "For fixed lowercase letters, counting removes the sort cost and stays `O(k)`."
- `128`: "Only sequence starts expand, so each number is consumed by the while-loop at most once."
- `560`: "At index i, I need earlier prefix sums equal to `prefix[i] - k`."
- `167`: "In a sorted array, if the sum is too small, only the left pointer can increase it."
- `11`: "Width always shrinks, so only replacing the shorter wall can raise the area bound."
- `15`: "Dedup the fixed index first, then dedup both moving pointers only after recording a hit."

## Failure Criteria

- Any gate problem solved only after reading an editorial
- Any template cannot be rewritten from memory within 5 minutes
- Complexity explanation contains hand-wavy language instead of an invariant
- `560` explained with sliding-window logic
- `15` dedup order stated incorrectly
- Total training time exceeds 135 minutes

## Upgrade Rule

- If either anagram problem fails, tomorrow repeats Day 2 with another key-design comparison problem.
- If either hash-sequence/prefix problem fails, tomorrow repeats Day 2 with another prefix-counting comparison problem.
- Only a full pass across all four gate problems and all three release problems allows entry into Day 3 sliding window work.

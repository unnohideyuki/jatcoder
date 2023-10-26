# Cheat Sheet
`java.util.*` にあるものの使い方アンチョコ

## PriorityQueue
C++ の priority_queue と違って、デフォルトが昇順（一番小さいものが top にくる）

降順にするには、Comparator を以下のように指定する。

```
new PriorityQueue<>(Collections.reverseOrder()))
```

よく使いそうなメソッド：
- `boolean add(E e)`:  挿入
- `peek()`: 先頭を取得するが削除はしない。空の場合は `null` を返します。
- `poll()`: 先頭を取得および削除する。空の場合は `null` を返します。
- `size()`: 要素の数を返します。


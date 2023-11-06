# Pair
２つの異なる型の値を保持する「組」を表現します。

`Pair` は `Comparable` であるため、`PriorityQueue` の要素や `HashMap` のキーとして使えます。
ただし、格納される２つの値は、いずれも `Comparable` でなければなりません。

## コンストラクタ
```
Pair<L, R> pair = new Pair<>(L a, R b)
```

## メンバ変数へのアクセサ

いずれも getter のみで、setter はありません。（Pair は immutable です）

###  first

```
L pair.getFirst()
```

１つめの要素

### second

```
R pair.getSecond()
```

２つめの要素

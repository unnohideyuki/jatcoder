# Segtree

これは AC Library の Segtree を移植したものです。

モノイド、つまり、以下を満たす ($S, \cdot: S \times S \rightarrow S, e \in S$)
に対して使用出来るデータ構造です。

- 結合律: $(a \cdot b) \cdot c = a \cdot (b \cdot c)$ for all $a, b, c \in S$
- 単位元の存在: $a \cdot e = e \cdot a = a$ for all $a \in S$

長さが $N$ であるような $S$ の配列に対し、

- 要素の１点変更
- 区間の要素の総積の取得

を $O(\log N)$ で行うことができます。

なお、このライブラリはオラクルとして `op`, `e` のふたつを使用しますが、
これらが定数時間で動くものとした場合の計算量を示します。オラクルの計算量が
$O(f(n))$ である場合は、すべての計算量が $O(f(n))$ 倍となります。

## コンストラクタ

```console
(1) Segtree<S> seg = new Segtree(BiFunction<S, S, S> op, S e, int n)
(2) Segtree<S> seg = new Segtree(BiFunction<S, S, S> op, S e, List<S> v)
```

それぞれ、

1. 長さ n の数列 a を作ります。初期値は全部 e となります。
2. 長さ n = v.size() の数列 a を作ります。v の内容が初期値となります。

Segtree には、

- 型 `S`
- 二項演算子 `BiFunction<S, S, S>`
- 単位元 `S e`

を指定する必要があります。たとえば、Range Min Query であれば、

```java
final BiFunction<Integer, Integer, Integer> op = 
        (a, b) -> { return Math.min(a, b); };
final Integer e = (int) 1e9;
final Segtree<Integer> seg = new Segtree<>(op, e, 10);
```

のようになります。

### 制約

$0 \le n \le 10^8$

### 計算量

$O(n)$

## set

```console
void seg.set(int p, S x)
```

`a[p]` に `x` を代入します。

### 制約

$0 \le p < n$

### 計算量

$O(\log n)$

## get

```console
S seg.get(int p)
```

`a[p]` を返します。

### 制約

$0 \le p < n$

### 計算量

$O(1)$

## prod

```console
S seg.prod(int l, int r)
```

`op(a[l], ..., a[r-1])` を計算します。l = r の時は `e` を返します。

### 制約

$0 \le l \le r \le n$

### 計算量

$O(\log n)$


## allProd

```console
S seg.allProd()
```

`op(a[l], ..., a[n-1])` を計算します。n = 0 の時は `e` を返します。

### 計算量

$O(1)$

## maxRight

```console
int seg.maxRight(int l, Function<S, Boolean> f)
```

`f(op(a[l], a[l+1], ..., a[r-1])) == true` となる最大の r を求めます。

### 制約

- `f` に副作用がない
- `f(e) == true`
- $0 \le l \le n$

### 計算量

$O(\log n)$

## minLeft

```console
int seg.minLeft(int r, Function<S, Boolean> f)
```

`f(op(a[l], a[l+1], ..., a[r-1])) == true` となる最小の l を求めます。

### 制約

- `f` に副作用がない
- `f(e) == true`
- $0 \le r \le n$

### 計算量

$O(\log n)$

## 使用例

AC Code for [AtCoder Library Practice Contest J - Segment Tree](https://atcoder.jp/contests/practice2/tasks/practice2_j):
[Practice2J](../code/src/main/java/jp/ne/sakura/uhideyuki/jatcoder/PRACTICE2J.java)
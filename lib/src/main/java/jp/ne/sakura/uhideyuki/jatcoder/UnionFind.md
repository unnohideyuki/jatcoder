# UnionFind

これは AC Library の DSU を移植したものです。

無向グラフに対して、以下をならし $O(\alpha(n))$ で処理することができる。

- 辺の追加
- ２頂点が連結かどうかの判定

## コンストラクタ

```agsl
UnionFind uf = new UnionFind(int n)
```

n 頂点 0 辺の無向グラフを作ります。

### 制約

- $0 \le n \le 10^8

### 計算量

- O(n)

## merge

```agsl
int d.merge(int a, int b)
```

辺 (a, b) を追加し、これを含む連結成分の代表元を返します。

### 制約

- $0 \le a < n$
- $0 \le b < n$

### 計算量

- ならし $O(\alpha(n))$

### 使用例

AC Code for [AtCoder Library Practice Contest A - Disjoint Set Union](https://atcoder.jp/contests/practice2/tasks/practice2_a): 
[Practice2A.java](../.././../../../../../../../work/Practice2A/Practice2A.java)

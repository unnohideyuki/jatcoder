# UnionFind

これは AC Library の DSU を移植したものです。

無向グラフに対して、以下をならし $O(\alpha(n))$ で処理することが出来ます。

- 辺の追加
- ２頂点が連結かどうかの判定

## コンストラクタ

```console
UnionFind uf = new UnionFind(int n)
```

n 頂点 0 辺の無向グラフを作ります。

### 制約

- $0 \le n \le 10^8$

### 計算量

- O(n)

## merge

```console
int uf.merge(int a, int b)
```

辺 (a, b) を追加し、これを含む連結成分の代表元を返します。

### 制約

- $0 \le a < n$
- $0 \le b < n$

### 計算量

- ならし $O(\alpha(n))$

## same

```console
boolean uf.same(int a, int b)
```

頂点 a, b が連結かどうかを返します。

### 制約

- $0 \le a < n$
- $0 \le b < n$

### 計算量

- ならし $O(\alpha(n))$

## leader

```console
int uf.leader(int a)
```

頂点 a の属する連結成分の代表元を返します。

### 制約

- $0 \le a < n$

### 計算量

- ならし $O(\alpha(n))$

## size

```console
int uf.size(int a)
```

頂点 a の属する連結成分のサイズを返します。

### 制約

- $0 \le a < n$

### 計算量

- ならし $O(\alpha(n))$

## groups

```console
ArrayList<ArrayList<Integer>> uf.groups()
```

グラフの連結成分のリストを返します。

返り値は「各連結成分に含まれる頂点番号のリスト」のリストです。いずれも格納される順序は未定義です。

### 計算量

- ならし $O(n)$

### 使用例

AC Code for [AtCoder Library Practice Contest A - Disjoint Set Union](https://atcoder.jp/contests/practice2/tasks/practice2_a): 
[Practice2A.java](../code/src/main/java/jp/ne/sakura/uhideyuki/jatcoder/Practice2A.java)

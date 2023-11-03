# FenwickTree

これは AC Library の Fenwick Tree を移植したものです。

長さ $N$ の配列に対し、以下を $O(\log N)$ で求めることが出来るデータ構造です。

- 要素の１点変更
- 区間の要素の総和

## Fenwick Tree の生成

Integer, Long, Modint の FenwickTree は、以下のように生成することが出来ます。

```console
(1) FenwickTree<Integer> fw = FenwickTree.integerTree(int n);
(2) FenwickTree<Long> fw = FenwickTree.longTree(int n);
(3) FenwickTree<Modint> fw = 
      FenwickTree.modintTree(int n, Modint.Builder modint); 
```

長さ n の配列 `a[n]` を作り、全ての要素を 0 で初期化します。

なお、FenwickTree クラスのコンストラクタを直接用いて生成することも可能です。
コンストラクタのシグネチャは以下の通りです。

```console
public FenwickTree(int n, 
                   T[] data,
                   BiFunction<T, T, T> fadd,
                   BiFunction<T, T, T> fsub,
                   T zero)
}
```

`FenwickTree<T>` を生成するためには、以下ように引数を指定する必要があります。

- int n : 配列のサイズ
- T[] data :  全て 0 で初期化された、サイズ n の配列
- BiFunction<T, T, T> fadd, fsub : T の加算、除算を行う関数
- T zero : 型 T におけるゼロ

### 制約

$0 \le n \le 10^8$

### 計算量

$O(n)$

## add

```console
void fw.add(int p, T x)
```

`a[p] += x` を行う。

### 制約

$0 \le p < n$

## sum

```console
T fw.sum(int l, int r)
```

`a[l] + a[l + 1] + … + a[r - 1]` を返す。

### 制約

$0 \le l \le r \le n$

### 計算量

$O(\log n)$

## 使用例

AC Code for
[AC Library Practice Contest B - Fenwick Tree](https://atcoder.jp/contests/practice2/tasks/practice2_b):
[Practice2B](../code/src/main/java/jp/ne/sakura/uhideyuki/jatcoder/Practice2B.java)

# AC - 共通ユーティリティ
競技プログラミングであると便利そうな小さな関数群をまとめました

## gcd

```java
long AC.gcd(long a, long b)
int AC.gcd(int a, int b)
```

２数の最大公約数を求めます。

## iota

```java
    void iota(List<Integer> list, Integer value, int size)
    void iota(List<Long> list, Long value, int size)
    void iota(int[] arr, int value)
    void iota(long[] arr, long value)
    void iota(Integer[] arr, Integer value)
    void iota(Long[] arr, Long value)
```

指定された値から始まる整数列を生成します。

List に対しては `add` で追加される要素の個数を引数 `size` で指定します。

## lcm

```java
long AC.lcm(long a, long b)
int AC.lcm(int a, int b)
```

２数の最小公倍数を求めます。

## lower_bound

```java
int AC.lower_bound(List<T extends Comparable<? super T>> list, T key)
```

list において、指定された要素以上の値が現れる最初の位置のインデックスを求めます。

## next_permutation

```java
boolean next_permutation(List<<T extends Comparable<? super T>>> list)
```

与えられた時点のリストを起点の順列として、辞書順のよる次の順列を生成します。

次の順列が存在する場合は `true`、そうでなければ `false` を返します。

## upper_bound

```java
int AC.upper_bound(List<T extends Comparable<? super T>> list, T key)
```

list において、指定された要素より大きい値が現れる最初の位置のインデックスを求めます。




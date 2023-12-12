# AC - 共通ユーティリティ
競技プログラミングであると便利そうな小さな関数群をまとめました

## gcd

```console
long AC.gcd(long a, long b)
int AC.gcd(int a, int b)
```

２数の最大公約数を求めます。

## iota

```console
void AC.iota(List<Integer> list, Integer value, int size)
void AC.iota(List<Long> list, Long value, int size)
void AC.iota(int[] arr, int value)
void AC.iota(long[] arr, long value)
void AC.iota(Integer[] arr, Integer value)
void AC.iota(Long[] arr, Long value)
```

指定された値から始まる整数列を生成します。

List に対して用いる場合には、サイズを第二引数 `size` で指定します。
第一引数として渡すリストの元のサイズはなんでも構いません。


## lcm

```console
long AC.lcm(long a, long b)
int AC.lcm(int a, int b)
```

２数の最小公倍数を求めます。

## lowerBound

```console
int AC.lowerBound(List<T extends Comparable<? super T>> list, T key)
```

list において、指定された要素以上の値が現れる最初の位置のインデックスを求めます。

## nextPermutation 
### List 版

```console
(1) boolean AC.nextPermutation(List<<T extends Comparable<? super T>>> list)
```

与えられた時点のリストを起点の順列として、辞書順による次の順列を生成します。

次の順列が存在する場合は `true`、そうでなければ `false` を返します。

### String 版

```console
(2) String AC.nextPermutation(String s)
```

String 版では、次の順列が存在する場合にはその文字列を、そうでなければ `null` を返します。

## upperBound

```console
int AC.upperBound(List<T extends Comparable<? super T>> list, T key)
```

list において、指定された要素より大きい値が現れる最初の位置のインデックスを求めます。

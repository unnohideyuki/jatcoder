# Modint

これは AC Library の Modint を移植したものです。

ただし、Java ではオリジナルの C++ と同様のことが出来なかったため、いくらか変更しています。

- オリジナルにあった static 版のメリットを再現出来なかったため、dynamic 版のみとした
- 演算子のオーバーロードが出来ないので、インタフェースは独自に (Java 風に) 定義しなおした

## Modint.Builder

`Modint` はコンストラクタを直接呼ぶのではなく、 `Modint.Builder` を使って生成します。
builder には mod (剰余演算の法) を設定します。

```console
(1) Modint.Builder modint = new Modint.Builder(int m);
(2) Modint.Builder modint = new Modint.Builder(int m, boolean prime);
```

`prime` は、mod が素数である時 `true`、そうでないときには `false`  を設定してください。
この値によって、除算や逆元の計算に用いられる値が変わります。

mod が素数でないにもかかわらず `prime` を `true` に設定した場合には、
演算結果は不定となります。

`prime` を省略した場合には、原則として `false` が設定されますが、
mod に指定された値が 998244353 か 1000000007 のいずれかだった場合には `true`
が自動的に設定されます。

### build()

```console
(1) Modint x = modint.build();
(2) Modint x = modint.build(int v);
(3) Modint x = modint.build(long v);
```

値を指定しなかった場合には、0 が設定されます。

## Modint クラスのメソッド

### setValue

```console
Modint x.setValue(int v)
```

オブジェクトの値を設定します（破壊的変更）。

### getValue

```console
int x.getValue()
```

x に格納されている値を返します。

### mod

```console
int x.mod()
```
mod （剰余演算の法）を返します。

### 加減乗除、逆数、累乗

```console
Modint x.add(Modint y)
Modint x.sub(Modint y)
Modint x.mul(Modint y)
Modint x.div(Modint y)
Modint x.inv()
Modint x.pow(long n)
```

これらの関数は演算の結果を返しますが、
オブジェクトに対して破壊的変更を加えるわけではなことに注意してください。
例えば、`x = x + y` に相当することをしたい場合には、以下のように書く必要があります。

```console
x = x.add(y);
```











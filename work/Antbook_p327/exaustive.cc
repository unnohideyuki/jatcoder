#include <bits/stdc++.h>
#include <cassert>
using namespace std;
using ll = long long;
const double pi = 3.14159265359;
const ll INF = 1LL << 60;

template<class T> inline bool chmin(T& a, T b){ if (a > b){a = b; return true;} return false;}
template<class T> inline bool chmax(T& a, T b){ if (a < b){a = b; return true;} return false;}

int main()
{
  cin.tie(0); cout.tie(0); ios::sync_with_stdio(false);

  int n, k;
  string s;
  cin >> n >> k >> s;
  
  string agct = "AGCT";

  ll ans = 0;
  for (ll i = 0; i < (1L << (n*2)); i++){
    string t = "";
    for (int j = 0; j < n; j++) {
      t += agct[(i >> j*2) & 3];
    }

    bool ng = false;

    for (int j = 0; j <= n - s.length(); j++){
      if (t.substr(j, s.length()) == s){
        ng = true;
        break;
      }
    }

    if (!ng) {
      ans++;
      ans %= 10009;
    }
  }

  cout << ans << endl;
  return 0;
}

package jp.ne.sakura.uhideyuki.jatcoder;
import org.w3c.dom.Node;

import java.util.*;
import java.io.PrintWriter;

public class Abc328d {
  private final FastScanner sc;
  private final PrintWriter out;
  public Abc328d(){
    sc = new FastScanner();
    out = new PrintWriter(System.out);
  }
  public void flush() {}

  static class Node {
    Node prev;
    Node next;
    char c;

    public Node() {
      prev = null;
      next = null;
      c = '.';
    }

    public Node(final Node prev, final char c) {
      this.prev = prev;
      this.next = null;
      this.c = c;
      prev.next = this;
    }
  }

  public void solve(){
    final Node st = new Node();
    final String s = sc.next();
    Node p = st;
    for (int i = 0; i < s.length(); i++) {
      p = new Node(p, s.charAt(i));
    }
    new Node(p, '\n');

    p = st;

    while (p != null) {
      if (p.c == 'C' && p.prev != null && p.prev.prev != null && p.prev.c == 'B' && p.prev.prev.c == 'A') {
        Node q = p.next;
        q.prev = p.prev.prev.prev;
        q.prev.next = q;
      }
      p = p.next;
    }

    p = st.next;
    final StringBuilder sb = new StringBuilder();
    while (p != null){
      sb.append(p.c);
      p = p.next;
    }
    out.print(sb.toString());
    out.flush();
  }
}

#!/usr/bin/env ruby
require 'erb'

LIBDIR = "../../lib/src/main/java/jp/ne/sakura/uhideyuki/jatcoder"

LIB_CLASSES =
  Dir.glob("#{LIBDIR}/*.java").map{|s| File.basename(s, ".java")}

template = <<'EOS'
import java.util.*;

public class Main {
    public static void main(final String[] args){
        <%= clsname %>.solve();
    }
}

EOS

## Read the source from stdin

instr = $stdin.read
instr.gsub(/^package.*$/, "")

## Detect used libraries

libs = []
LIB_CLASSES.each{|s|
  re = Regexp.new("#{s}[\.\:\(]")
  if re =~ instr
    libs.append(s)
  end
}

## Print Main
re1 = /^\s*public\s+class\s+(\w+)/
re1 =~ instr
clsname = $1
erb = ERB.new(template)
erb.run

## Insert source
re2 = /^\s*(import|package).*$/
instr = instr.gsub(re2, "");
instr = instr.gsub(re1, "class #{clsname}")
puts instr

## Insert libs
libs.each{|s|
  File.open("#{LIBDIR}/#{s}.java") do |f|
    str = f.read
    str = str.gsub(re1, "class #{s}")
    str = str.gsub(re2, "")
    puts str
  end
}

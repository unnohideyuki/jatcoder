#!/usr/bin/env ruby
require 'erb'
require 'set'

LIBDIR = "../../lib/src/main/java/jp/ne/sakura/uhideyuki/jatcoder"

LIB_CLASSES =
  Dir.glob("#{LIBDIR}/*.java").map{|s| File.basename(s, ".java")}

template = <<'EOS'
<%= importstr %>

public class Main {
    public static void main(final String[] args){
        <%= clsname %>.solve();
    }
}

EOS

st = Set.new()

## Read the source from stdin

instr = ""
# collect imports from input file
while s = $stdin.gets do
  unless /^package.*$/ =~ s
    instr += s
  end
  if /^\s*import/ =~ s
    st.add(s.strip)
  end         
end

## Detect used libraries

libs = []
LIB_CLASSES.each{|s|
  re = Regexp.new("#{s}\s*[\.\:\(\<]")
  if re =~ instr
    libs.append(s)
  end
}

# collect imports from lib
libs.each{|s|
  File.open("#{LIBDIR}/#{s}.java") do |f|
    while s = f.gets do
      if /^\s*import/ =~ s
        st.add(s.strip)
      end         
    end
  end
}


importstr = ""
st.each{|s| importstr += "#{s}\n"}

## Print Main
re1 = /^\s*public\s+class\s+(\w+)/
re1 =~ instr
clsname = $1
puts "// Main.java is converted from #{clsname}.java by genmain.rb:"
puts "//   https://github.com/unnohideyuki/jatcoder/blob/5cf3ba7375516e84c3663c25e8d9d09b212b2ab5/work/genmain.rb"
erb = ERB.new(template)
erb.run

## Insert source
re2 = /^\s*(import|package).*$/
instr = instr.gsub(re2, "");
instr = instr.gsub(re1, "class #{clsname}")
puts instr

## Insert libs

#### work around ####
if (libs.include?("FenwickTree") and (not libs.include?("Modint")))
  libs.append("Modint")
end

libs.each{|s|
  File.open("#{LIBDIR}/#{s}.java") do |f|
    str = f.read
    str = str.gsub(re1, "class #{s}")
    str = str.gsub(re2, "")
    puts str
  end
}

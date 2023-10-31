#!/usr/bin/env ruby
require 'set'

CODE_PATH = "../../code/src/main/java/jp/ne/sakura/uhideyuki/jatcoder"
CODE_BUILD_PATH = "../../code/build/classes/java/main/"
CLASS_PATH = "../../lib/src/main/java"
JATCODER_LIBS_DIR = "../../lib/src/main/java/jp/ne/sakura/uhideyuki/jatcoder"

$source_class = File.basename(ARGV[0], ".*")

$libs = Set.new

def command(str)
  # puts(str)
  system(str)
  if $? != 0
    exit $?
  end
end

def jdeps(class_name)
  source =
    "#{CODE_BUILD_PATH}/jp/ne/sakura/uhideyuki/jatcoder/#{$source_class}.class"
  if class_name != $source_class 
    source = "#{JATCODER_LIBS_DIR}/#{class_name}.class"
  end

  st = Set.new
  
  IO .popen("jdeps -verbose:class -filter:none -cp #{CLASS_PATH} #{source}",
            "r+") do |f|
    while s = f.gets
      if /->\s*jp\.ne\.sakura\.uhideyuki\.jatcoder\.(\w+)/ =~ s
        name = $1
        unless $libs.include?(name)
          st.add(name)
        end
      end
    end
  end

  if class_name != $source_class
    $libs.add(class_name)
  end

  st.each do |n|
    jdeps(n)
  end
end


#### compile
command("mkdir -p #{CODE_BUILD_PATH}/jp/ne/sakura/uhideyuki/jatcoder")
command("javac -d #{CODE_BUILD_PATH} -cp #{CLASS_PATH} #{$source_class}.java")

#### jdeps
jdeps($source_class)

#### print
$libs.each{|n| print("#{n} ")}
puts






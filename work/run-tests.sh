#!/bin/bash
# -*- mode:Shell -*-

passed=0
failed=0
for d in *
do
    if [ -d $d -a -f $d/$d.java -a -f $d/input1 ];then
       echo "====" entering $d ...
       cd $d
       ../compile $d.java
       if [ $? -ne 0 ]; then
	   echo "COMPILE FAILED"
	   failed=`expr $failed \+ 1`
       else
	   ../check
	   if [ $? -ne 0 ]; then
	       echo "CHECK FAILED"
	       failed=`expr $failed \+ 1`
	   else
	       passed=`expr $passed \+ 1`
	   fi
       fi
       cd ..
    fi
done

echo "passed: " $passed ", failed: " $failed
exit $failed



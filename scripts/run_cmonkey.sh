#!/bin/sh
mkdir -p $1
echo "$1 is output"
java -jar $KB_TOP/lib/jars/cmonkey/cmonkey.jar $@
tar cvfz $1.tgz $1    
rm -rf $1
mv $1.tgz $1
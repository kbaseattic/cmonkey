#!/bin/bash
mkdir -p $2
echo "$2 is output"
export JAVA_HOME=$KB_RUNTIME/java
java -jar $KB_TOP/lib/jars/cmonkey/cmonkey.jar $@ 2> $2/error.log
tar cvfz $2.tgz $2
cp $2.tgz /var/tmp/cmonkey
rm -rf $2

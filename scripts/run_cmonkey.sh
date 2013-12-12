#!/bin/sh
echo "$2 is output"
java -jar $KB_TOP/lib/jars/cmonkey/cmonkey.jar $@
tar cvfz $2.tgz $2
rm -rf $2

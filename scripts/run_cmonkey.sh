#!/bin/sh
mkdir -p $2
cat >> /var/tmp/cmonkey.txt << EOF
job $2 started
EOF
echo "$2 is output"
java -jar $KB_TOP/lib/jars/cmonkey/cmonkey.jar $@ >> /var/tmp/cmonkey.txt
tar cvfz $2.tgz $2
rm -rf $2
cat >> /var/tmp/cmonkey.txt << EOF
job $2 finished
EOF

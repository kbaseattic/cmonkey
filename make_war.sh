#!/bin/bash

if (( $# != 1 ))
then
    echo "Usage: make_war <servlet_class>"
    exit
fi

SERVLET_CLASS=$1
DATE=$(date +"%Y%m%d%H%M")
WAR_DIR=./war_$DATE
WEB_INF_DIR=$WAR_DIR/WEB-INF
CURRENT_DIR="$(pwd)"
DIST_DIR=$CURRENT_DIR/dist
WAR_FILE=$DIST_DIR/service.war
SRC_DIR=./src
LIB_DIR=./lib
CLASSES_DIR=$WEB_INF_DIR/classes
CLASSPATH=

test -d "$DIST_DIR" || mkdir "$DIST_DIR"

if [ -d $LIB_DIR ]; then
    for a in $LIB_DIR/*.jar; do
	if [ $CLASSPATH ]  
	then  
	    CLASSPATH=$CLASSPATH:$a
	else  
            CLASSPATH=$a
        fi
    done
    CLASSPATH="-classpath $CLASSPATH"
fi

mkdir $WAR_DIR
mkdir $WEB_INF_DIR
mkdir $CLASSES_DIR

JAVA_FILE=${SERVLET_CLASS//\./\/}
javac -sourcepath $SRC_DIR $CLASSPATH -d $CLASSES_DIR -g $SRC_DIR/$JAVA_FILE.java

if [ -d $LIB_DIR ]; then
    cp -R $LIB_DIR $WEB_INF_DIR/
fi
cat > $WEB_INF_DIR/web.xml <<EOF
<?xml version="1.0" encoding="UTF-8"?>
<web-app>
    <servlet>
	<servlet-name>RootServlet</servlet-name>
	<servlet-class>$SERVLET_CLASS</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>RootServlet</servlet-name>
        <url-pattern>/*</url-pattern>
    </servlet-mapping>
</web-app>
EOF
if [ -f $WAR_FILE ]
then
    rm $WAR_FILE
fi
cd $WAR_DIR
zip -r $WAR_FILE WEB-INF
#tar -zcf $WAR_FILE WEB-INF
cd ..
rm -rf $WAR_DIR

#SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
#. $SCRIPT_DIR/glassfish_deploy_war.sh $CURRENT_DIR/$WAR_FILE $TARGET_PORT

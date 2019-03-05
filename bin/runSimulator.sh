#!/bin/bash

################################################################################################################
## ONLY CHANGE THESE VARIABLES IN THIS SECTION
## THE REST OF THE SCRIPT SHOULD WORK FINE WITHOUT NEEDING ANY CHANGE FROM THE USER

## SET THE JAVA_HOME FIRST. THIS SHOULD BE AN ABSOLUTE PATH THE DIRECTORY WHERE JAVA FILES ARE INSTALLED
## THIS SHOULD NOT POINT TO THE 'bin' DIRECTORY
JAVA_HOME=/usr

## MINIMUM MEMORY TO BE ALLOCATED TO THE JVM, IN MEGABYTES
MIN_MEMORY=4096

## MAXIMUM MEMORY TO BE ALLOCATED TO THE JVM, IN MEGABYTES
MAX_MEMORY=8192

## LOG FILE NAME DATE FORMAT
## THIS IS AN ADVANCE SETTING, PLEASE DON'T PLAY WITH IT UNLESS YOU'RE AN EXPERT IN BASH 'date' COMMAND FORMAT
LOG_FILE_DATE_FORMAT=%Y%b%d%H%M%S

## IF YOU ARE GETTING PROBLEM WITH THE CLIENT COMPLAINING THE HOST/SERVER COULDN'T BE FOUND YOU SHOULD SET THIS
JAVA_RMI_HOST=10.8.2.75
#JAVA_RMI_HOST=10.202.1.12

## SET THIS IF YOU WANT TO ENABLE JMX
ENABLE_JMX=true
JMX_PORT=8000

## SET THIS IF YOU WANT TO ENABLE CUSTOM DIFFERENT GC
GC=+UseConcMarkSweepGC

## OTHER JAVA OPTIONS
## USE THIS SECTION TO SPECIFY ANY OTHER JAVA COMMAND LINE OPTIONS, SUCH AS -D
## IF THERE ARE NO OTHER OPTIONS, JUST COMMENT OUT THE LINE BELOW
#OTHER_OPTS=

## END OF CHANGEABLE SETTINGS
## PLEASE DON'T CHANGE ANYTHING AFTER THIS LINE
################################################################################################################

if [ -f $JAVA_HOME/bin/java ]; then
 JAVA=$JAVA_HOME/bin/java
else
 JAVA=`which java`
 if [ $? -ne 0 ]; then
  echo "Unable to find Java, plase ensure JAVA_HOME or path to Java is set in your environment!"
  exit 1
 fi
fi

echo $MIN_MEMORY | grep [^0-9] > /dev/null 2>&1
if [ "$?" -eq "0" ]; then
 echo "MIN_MEMORY value must be a valid number"
 exit 1
fi

echo $MAX_MEMORY | grep [^0-9] > /dev/null 2>&1
if [ "$?" -eq "0" ]; then
 echo "MAX_MEMORY value must be a valid number"
 exit 1
fi

CurDir=`dirname $0`
HomeDir=$CurDir/..
cd $CurDir


RESOURCES=$HomeDir/resources
LIB=$HomeDir/lib
LOGS=$HomeDir/logs


CLASSPATH=$LOGS
CLASSPATH=$CLASSPATH:$LIB/marsexplorer-0.0.1-SNAPSHOT.jar
CLASSPATH=$CLASSPATH:$LIB/slf4j-api-1.7.25.jar
CLASSPATH=$CLASSPATH:$LIB/logback-core-1.2.3.jar
CLASSPATH=$CLASSPATH:$LIB/logback-classic-1.1.11.jar
CLASSPATH=$CLASSPATH:$LIB/groovy-all-2.4.15.jar


LOG_FILE=$LOGS/MarsExplorerSimulator-`date +"$LOG_FILE_DATE_FORMAT"`.log

JAVA_OPTS="-classpath $CLASSPATH"
JAVA_OPTS=$JAVA_OPTS" -Xms"$MIN_MEMORY"m"
JAVA_OPTS=$JAVA_OPTS" -Xmx"$MAX_MEMORY"m"
JAVA_OPTS=$JAVA_OPTS" -Djava.awt.headless=true"
JAVA_OPTS=$JAVA_OPTS" -Djava.io.tmpdir="$LOGS/tmp

if [ -n "$JAVA_RMI_HOST" ]; then
 JAVA_OPTS=$JAVA_OPTS" -Djava.rmi.server.hostname="$JAVA_RMI_HOST
fi

if [ "$ENABLE_JMX" == "true" -o "$ENABLE_JMX" == "TRUE" ]; then
  if [ -n "$JMX_PORT" ]; then
   echo $JMX_PORT | grep [^0-9] > /dev/null 2>&1
	 if [ "$?" -eq "0" ]; then
 		echo "JMX_PORT value must be a valid number"
 		exit 1
 	 fi
 	else
 	 echo "JMX port not provided, defaulting to 8000"
 	 JMX_PORT=8000
	fi
	JAVA_OPTS=$JAVA_OPTS" -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=$JMX_PORT -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false"
fi

if [ -n "$GC" ]; then
 JAVA_OPTS=$JAVA_OPTS" -XX:PermSize=512m -XX:MaxPermSize=1024m -XX:+CMSClassUnloadingEnabled -XX:+CMSPermGenSweepingEnabled -XX:"$GC
fi

if [ -n "$OTHER_OPTS" ]; then
 JAVA_OPTS=$JAVA_OPTS" $OTHER_OPTS"
fi

$JAVA $JAVA_OPTS -Xloggc:$LOGS/gc.log -XX:+PrintGCDetails com.powerapps.monitor.StartLogMonitor 1>$LOG_FILE 2>&1 &

echo $! > $LOGS/procid
echo $LOG_FILE > $LOGS/logname
chmod 770 $LOGS/logname
chmod 770 $LOGS/procid
chmod 770 $LOG_FILE
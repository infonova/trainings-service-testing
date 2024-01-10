#!/bin/bash
# usage: ./coverage.sh ../demo-application/target/demo-application-0.0.1-SNAPSHOT.jar ../demo-application  pom.xml jacoco

SERVICE_JAR="$1"
SERVICE_SOURCE_FOLDER="$2"
SERVICE_TEST_JAR="$3"
OUTPUT_DIR="$4"

if [ "$#" -ne 4 ]; then
  echo "Usage ./coverage.sh <service_jar> <service_dir> <service_test_jar> <output_dir>"
  exit 9
fi

TEMP_DIR=temp1

mkdir $TEMP_DIR
cd $TEMP_DIR
echo "Download jacoco"
wget -O jacoco.zip https://search.maven.org/remotecontent?filepath=org/jacoco/jacoco/0.8.11/jacoco-0.8.11.zip
echo "Unzip jacoco"
unzip -qq jacoco.zip
cd ..

java -javaagent:$TEMP_DIR/lib/jacocoagent.jar=destfile=$TEMP_DIR/test.exec,output=file,dumponexit=true -jar $SERVICE_JAR &

# unfortunately no actuator implemented :(

#attempt_counter=0
#max_attempts=5

#until $(curl --output /dev/null --fail http://localhost:8080/actuator/health); do
#    if [ ${attempt_counter} -eq ${max_attempts} ];then
#      echo "Max attempts reached"
#      exit 1
#    fi
#
#    printf '.'
#    attempt_counter=$(($attempt_counter+1))
#    sleep 5
#done
sleep 15
mvn -f $SERVICE_TEST_JAR verify
java -jar $TEMP_DIR/lib/jacococli.jar report $TEMP_DIR/test.exec --sourcefiles=$SERVICE_SOURCE_FOLDER/src/main/java --classfiles $SERVICE_SOURCE_FOLDER/target/classes --html $OUTPUT_DIR

echo "Cleanup"
rm -r $TEMP_DIR
kill -9 $(ps aux | grep $SERVICE_JAR | awk '{print $2}')

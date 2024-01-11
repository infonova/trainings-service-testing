#!/bin/bash
# usage: ./coverage.sh ../demo-application/target/demo-application-0.0.1-SNAPSHOT.jar ../demo-application  . jacoco

SERVICE_JAR="$1"
SERVICE_SOURCE_FOLDER="$2"
SERVICE_TEST_LIB_FOLDER="$3"
OUTPUT_DIR="$4"

if [ "$#" -ne 4 ]; then
  echo "Usage ./coverage.sh <service_jar> <service_dir> <service_test_dir> <report_output_location>"
  exit 9
fi

TEMP_DIR=temp_dir-$(date -d "today" +"%Y%m%d%H%M")
CONTAINER_PATH=/app/coverage

cleanup() {
  echo "Cleanup"
  rm -rf $ABSOLUTE_TEMP_DIR
  rm $SERVICE_SOURCE_FOLDER/.env
}

resolve_relative_path() (
    # file is dir
    if [ -d "$1" ]; then
        cd "$1" || return 1
        pwd
    elif [ -e "$1" ]; then
        if [ ! "${1%/*}" = "$1" ]; then
            cd "${1%/*}" || return 1
        fi
        echo "$(pwd)/${1##*/}"
    else
        return 1 # Failure, neither file nor directory exists.
    fi
)

SERVICE_JAR=$(resolve_relative_path $SERVICE_JAR)
SERVICE_SOURCE_FOLDER=$(resolve_relative_path $SERVICE_SOURCE_FOLDER)
SERVICE_TEST_LIB_FOLDER=$(resolve_relative_path $SERVICE_TEST_LIB_FOLDER)
SERVICE_TEST_POM=$SERVICE_TEST_LIB_FOLDER/pom.xml

mkdir $TEMP_DIR
cd $TEMP_DIR
echo "Download jacoco"
wget -O jacoco.zip https://search.maven.org/remotecontent?filepath=org/jacoco/jacoco/0.8.11/jacoco-0.8.11.zip
echo "Unzip jacoco"
unzip -qq jacoco.zip
cd ..

ABSOLUTE_TEMP_DIR=$(resolve_relative_path $TEMP_DIR)
echo COVERAGE_VOLUME=$ABSOLUTE_TEMP_DIR:$CONTAINER_PATH >> $SERVICE_SOURCE_FOLDER/.env
echo JAVA_ARGS=-javaagent:$CONTAINER_PATH/lib/jacocoagent.jar=destfile=$CONTAINER_PATH/test.exec,output=file,dumponexit=true >> $SERVICE_SOURCE_FOLDER/.env

echo "Print env file"
cat $SERVICE_SOURCE_FOLDER/.env

echo "start docker container"
docker-compose -f $SERVICE_SOURCE_FOLDER/docker-compose.yml -f docker-compose.coverage.yml up -d --build
WORK_DIR=$(pwd)



attempt_counter=0
max_attempts=10

until $(curl --output /dev/null --fail http://localhost:8080/actuator/health); do
    if [ ${attempt_counter} -eq ${max_attempts} ];then
      echo "Max attempts reached"
      cleanup
      exit 1
    fi

    printf '.'
    attempt_counter=$(($attempt_counter+1))
    sleep 5
done

echo "start service tests"
mvn -f $SERVICE_TEST_POM verify

echo "stop docker container"
docker-compose -f $SERVICE_SOURCE_FOLDER/docker-compose.yml -f $WORK_DIR/docker-compose.coverage.yml down

echo "create output dir"
mkdir $OUTPUT_DIR
OUTPUT_DIR=$(resolve_relative_path $OUTPUT_DIR)

cd ..
java -jar $ABSOLUTE_TEMP_DIR/lib/jacococli.jar report $ABSOLUTE_TEMP_DIR/test.exec --sourcefiles=$SERVICE_SOURCE_FOLDER/src/main/java --classfiles $SERVICE_SOURCE_FOLDER/target/classes --html $OUTPUT_DIR

cleanup

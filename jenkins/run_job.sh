echo "---------------------sleep---------------------------"
sleep 40
curl -X POST --user ${JENKINS_USER}:${JENKINS_PASSWORD} http://jenkins:8080/job/${JOB_NAME_1}/build
echo "------------------job------------------------------"
java -jar /elasticsearch/build/libs/*.jar
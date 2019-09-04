#Expected test result.
EXPECTED=1

export ELASTIC_SERVER=test_elastic
export ELASTIC_SERVER_PORT=9201
export SPRING_SERVER_PORT=8989

docker-compose -f test/docker-compose.yml up -d

#Wait for the Elastic Search server to start.
sleep 35

#Insert the test documents into the database.
curl -d "docsPath=TestSample" http://${ELASTIC_SERVER}:${SPRING_SERVER_PORT}/index 1>/dev/null

sleep 10
#Test a simple query on the indexed files.
RESULT=$(curl -d "query=academic" http://${ELASTIC_SERVER}:${SPRING_SERVER_PORT}/search | grep -o p01_theSemanticWeb | grep -c p01_theSemanticWeb)

#Check the obtained result is equal to the expected and drop the environment.
if [ "$RESULT" -eq "$EXPECTED" ]; then
    echo true
    #docker-compose -f test/docker-compose.yml down
    exit 0
else
    echo false
    #docker-compose -f test/docker-compose.yml down
    exit 1
fi

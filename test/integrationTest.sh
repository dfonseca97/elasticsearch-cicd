
#Expected test result.
EXPECTED=1

docker-compose -f test/docker-compose.yml up -d

#Wait for the Elastic Search server to start.
sleep 20

#Insert the test documents into the database.
curl -s -d "docsPath=TestSample" http://localhost:8080/index 1>/dev/null

#Test a simple query on the indexed files.
RESULT=$(curl -s -d "query=academic" http://localhost:8080/search | grep -o p01_theSemanticWeb | grep -c p01_theSemanticWeb)

#Check the obtained result is equal to the expected and drop the environment.
if [ "$RESULT" -eq "$EXPECTED" ]; then
    echo true
    docker-compose -f test/docker-compose.yml down
    exit 0
else
    echo false
    docker-compose -f test/docker-compose.yml down
    exit 1
fi

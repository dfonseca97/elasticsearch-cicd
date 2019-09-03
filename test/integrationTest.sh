EXPECTED=1
docker-compose -f test/docker-compose.yml up -d
sleep 20
curl -s -d "docsPath=TestSample" http://localhost:8080/index 1>/dev/null
RESULT=$(curl -s -d "query=academic" http://localhost:8080/search | grep -o p01_theSemanticWeb | grep -c p01_theSemanticWeb)
if [ "$RESULT" -eq "$EXPECTED" ]; then
    echo true
    docker-compose -f test/docker-compose.yml down
    exit 0
else
    echo false
    docker-compose -f test/docker-compose.yml down
    exit 1
fi

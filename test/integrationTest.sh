EXPECTED=10
docker-compose up -d
curl -s -d "docsPath=TestSample" http://localhost:8080/index 1>/dev/null
RESULT=$(curl -s -d "query=academic" http://localhost:8080/search | grep -o p01_theSemanticWeb | grep -c p01_theSemanticWeb)
if [ "$RESULT" -eq "$EXPECTED" ]; then
    echo true
    docker-compose down
    exit 0
else
    echo false
    docker-compose down
    exit 1
fi

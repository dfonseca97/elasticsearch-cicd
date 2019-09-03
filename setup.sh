export ELASTIC_SERVER=$(cut -d " " -f1 <<<  $(hostname -I))
export ELASTIC_SERVER_PORT=9200

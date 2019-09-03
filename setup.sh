export SONAR_SERVER=$(cut -d " " -f1 <<<  $(hostname -I))
export SONAR_SERVER_PORT=9200

#!/bin/bash

PORTS=(9092 8084 5432 9999 8761)

check_port() {
  local PORT=$1
  local PID=$(lsof -iTCP:$PORT -sTCP:LISTEN -t)
  if [ -n "$PID" ]; then
    echo "Porta $PORT está ocupada pelo processo PID $PID ($(ps -p $PID -o comm=))"
    return 1
  else
    echo "Porta $PORT está livre"
    return 0
  fi
}

ALL_FREE=true
for PORT in "${PORTS[@]}"; do
  check_port $PORT || ALL_FREE=false
done

if [ "$ALL_FREE" = false ]; then
  echo "Erro: Algumas portas estão ocupadas. Libere-as antes de executar o docker-compose."
  exit 1
fi

echo "Iniciando o docker-compose..."
docker compose -f Delivery/docker-compose.yml down
docker compose -f Delivery/docker-compose.yml up -d

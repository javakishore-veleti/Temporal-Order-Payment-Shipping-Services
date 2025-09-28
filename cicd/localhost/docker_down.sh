THIS_SCRIPT_DIR=$(dirname $0)
echo "THIS_SCRIPT_DIR ${THIS_SCRIPT_DIR}"

echo "Stopping postgres"
# docker compose -f "${THIS_SCRIPT_DIR}"/postgres/docker-compose.yaml down

echo "Stopping temporal-server"
docker compose -f "${THIS_SCRIPT_DIR}"/temporal-server/docker-compose.yml down

docker ps -a